package com.anondo.taskmanager.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anondo.taskmanager.R
import com.anondo.taskmanager.databinding.ActivityShowTaskDataBinding

class Show_Task_Data : AppCompatActivity() {
    lateinit var binding: ActivityShowTaskDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowTaskDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var title = intent.getStringExtra("title")
        var description = intent.getStringExtra("description")
        var duedate = intent.getStringExtra("duedate")
        var status = intent.getStringExtra("status")

        binding.tvShowTitle.text = title
        binding.tvShowDescription.text = description
        binding.tvShowDueDate.text = "Due Date: "+duedate
        binding.tvShowStatus.text = status

        if (status=="Expired"){
            binding.tvShowStatus.setBackgroundResource(R.drawable.status_expired)
        } else if (status=="Pending"){
            binding.tvShowStatus.setBackgroundResource(R.drawable.status_pending)
        }else{
            binding.tvShowStatus.setBackgroundResource(R.drawable.status_completed)
        }

        binding.outlinedButton.setOnClickListener {

            finish()

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}