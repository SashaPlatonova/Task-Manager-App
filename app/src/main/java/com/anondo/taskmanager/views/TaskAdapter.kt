package com.anondo.taskmanager.views

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.anondo.taskmanager.R
import com.anondo.taskmanager.databinding.TaskItemBinding
import com.anondo.taskmanager.db.Task_Data_Class
import com.anondo.taskmanager.reducecode.EncryptDecrypt
import java.util.Date
import java.util.Locale

class TaskAdapter(var handleUser : handleUserClick, var context: Context, var taskData : MutableList<Task_Data_Class>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    interface handleUserClick{
        fun onEditClick(task: Task_Data_Class)
        fun onDeleteClick(task: Task_Data_Class)
        fun onStatusChange(task: Task_Data_Class , isCheck : Boolean)
    }

    class ViewHolder(var binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        var binding = TaskItemBinding.inflate(LayoutInflater.from(context) , parent , false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        holder.binding.apply {

            taskData[position].let {user->

                var title = EncryptDecrypt.decryptData(user.title)
                var description = EncryptDecrypt.decryptData(user.description)

                this.tvTitle.text = title
                this.tvDescription.text = description
                var status = user.status
                var millTime= user.duedate.toLong()

                val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = formatter.format(Date(millTime))

                this.tvDueDate.text = formattedDate

                var todaysDate = EncryptDecrypt.currentTime()

                if (status == true){

                    this.cbCompleted.isChecked = true
                    this.tvStatus.text = "Completed"
                    this.tvStatus.setBackgroundResource(R.drawable.status_completed)

                }
                else{
                    if (formattedDate>=todaysDate){
                        this.cbCompleted.isChecked = false
                        this.tvStatus.text = "Pending"
                        this.tvStatus.setBackgroundResource(R.drawable.status_pending)
                    }
                    else{
                        this.cbCompleted.isChecked = false
                        this.tvStatus.text = "Expired"
                        this.tvStatus.setBackgroundResource(R.drawable.status_expired)
                    }

                }

                this.cbCompleted.setOnClickListener {

                    handleUser.onStatusChange(user, cbCompleted.isChecked)

                    val isChecked = this.cbCompleted.isChecked

                    val dao = EncryptDecrypt.database(context)
                    taskData[position].apply {
                        val updatedTask = Task_Data_Class(
                            this.id,
                            this.title,
                            this.description,
                            this.duedate,
                            isChecked
                        )
                        dao.Edit_Task(updatedTask)
                    }

                }

                this.root.setOnClickListener {

                    var intent = Intent(context, Show_Task_Data::class.java)
                    intent.putExtra("title" , title)
                    intent.putExtra("description" , description)
                    intent.putExtra("duedate" , formattedDate)
                    intent.putExtra("status" , this.tvStatus.text)
                    context.startActivity(intent)


                }

                this.ivEditTask.setOnClickListener {

                    handleUser.onEditClick(user)

                }

                this.ivDeleteTask.setOnClickListener {

                   AlertDialog.Builder(context)
                        .setTitle("Warning")
                        .setIcon(R.drawable.con_warning_svgrepo_com)
                        .setMessage("Are you sure you want to delete this task?")
                        .setPositiveButton("Yes"){dialog,_ ->
                            handleUser.onDeleteClick(user)
                        }
                        .setNegativeButton("No"){dialog,_ ->
                            dialog.dismiss()
                        }
                        .show()
                }

            }

        }

    }

    override fun getItemCount(): Int {
        return taskData.size
    }
}