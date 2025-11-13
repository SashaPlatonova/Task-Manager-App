package com.anondo.taskmanager.views

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.anondo.taskmanager.R
import com.anondo.taskmanager.views.TaskAdapter
import com.anondo.taskmanager.db.TaskDao
import com.anondo.taskmanager.db.Task_Data_Class
import com.anondo.taskmanager.databinding.ActivityMainBinding
import com.anondo.taskmanager.reducecode.EncryptDecrypt

class MainActivity : AppCompatActivity() , TaskAdapter.handleUserClick {
    lateinit var binding: ActivityMainBinding
    lateinit var dao : TaskDao
    lateinit var taskList : MutableList<Task_Data_Class>
    private lateinit var taskAdapter: TaskAdapter
    private var titleMenuText = "Sort by Title(A-Z)"
    private var dueDateMenuText = "Due Date(OverDue)"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dao = EncryptDecrypt.database(this)

        recyclerAdapter(dao.Get_All_Task())

        binding.addTask.setOnClickListener {

            startActivity(Intent(this, Add_Task::class.java))
            finish()
        }

        binding.filter.setOnClickListener { view: View ->
            showMenu(view, R.menu.overflow_menu)
        }

        edSearchEvent()

    }


    fun updateMenu(){

        titleMenuText = "Sort by Title(A-Z)"
        dueDateMenuText = "Due Date(OverDue)"

        /*
        if (titleMenuText.contains("Sort by Title(A-Z)")){
            taskList.sortBy { EncryptDecrypt.decryptData(it.title) }
            taskAdapter.notifyDataSetChanged()
        }else{
            taskList.sortByDescending {  EncryptDecrypt.decryptData(it.title)}
            taskAdapter.notifyDataSetChanged()
        }

        if (dueDateMenuText.contains("Due Date(Closest)")){
            taskList.sortBy { it.duedate.toLong() }
            taskAdapter.notifyDataSetChanged()
        }else{
            taskList.sortByDescending { it.duedate.toLong() }
            taskAdapter.notifyDataSetChanged()
        }
*/
    }

    fun edSearchEvent(){
        binding.searchByTitle.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                var title = binding.searchByTitle.text.toString()

                if (!title.isEmpty()){

                    // use this method beacuse my title in database is encrypted
                    val allTasks = dao.Get_All_Task()

                    val filteredTasks = allTasks.filter { task ->
                        EncryptDecrypt.decryptData(task.title).contains(title, ignoreCase = true)
                    }
                    recyclerAdapter(filteredTasks.toMutableList())

                }
                else{
                    recyclerAdapter(dao.Get_All_Task())
                }

            }


        })

    }

    fun recyclerAdapter(daoo : MutableList<Task_Data_Class>){
        taskList = daoo
        taskList.sortBy { it.duedate.toLong() }

        taskAdapter = TaskAdapter(this@MainActivity, this@MainActivity, taskList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerView.adapter = taskAdapter


    }

    private fun showMenu(view: View, @MenuRes menuRes: Int) {

        val popup = PopupMenu(view.context, view)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.menu.findItem(R.id.titles).title = titleMenuText
        popup.menu.findItem(R.id.dueDates).title = dueDateMenuText

        popup.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.titles -> {
                    if (popup.menu.findItem(R.id.titles).title!!.contains("Sort by Title(A-Z)")){
                        taskList.sortBy { EncryptDecrypt.decryptData(it.title).lowercase() }
                        taskAdapter.notifyDataSetChanged()
                        titleMenuText = "Sort by Title(Z-A)"
                    }else{
                        taskList.sortByDescending {  EncryptDecrypt.decryptData(it.title).lowercase() }
                        taskAdapter.notifyDataSetChanged()
                        titleMenuText = "Sort by Title(A-Z)"
                    }

                    true
                }

                R.id.dueDates -> {

                    if (popup.menu.findItem(R.id.dueDates).title!!.contains("Due Date(OverDue)")){
                        taskList.sortByDescending { it.duedate.toLong() }
                        taskAdapter.notifyDataSetChanged()
                        dueDateMenuText = "Due Date(Closest)"
                    }else{
                        taskList.sortBy{ it.duedate.toLong() }
                        taskAdapter.notifyDataSetChanged()
                        dueDateMenuText = "Due Date(OverDue)"
                    }


                    true
                }else -> false
            }
        }
        popup.show()
    }

    override fun onEditClick(task: Task_Data_Class) {
        var editIntent = Intent(this, Add_Task::class.java)
        editIntent.putExtra("Edit" , task)
        startActivity(editIntent)
        finish()
    }

    override fun onDeleteClick(task: Task_Data_Class) {
        dao.Delete_Task(task)
        Toast.makeText(this , "Deleted Successful" , Toast.LENGTH_LONG).show()
        recyclerAdapter(dao.Get_All_Task())
    }

    override fun onStatusChange(task: Task_Data_Class, isChecked: Boolean) {
        val dao = EncryptDecrypt.database(this)

        val updatedTask = task.copy(status = isChecked)
        dao.Edit_Task(updatedTask)

        recyclerAdapter(dao.Get_All_Task())
    }

    override fun onResume() {
        super.onResume()
        recyclerAdapter(dao.Get_All_Task())
        updateMenu()
    }
}