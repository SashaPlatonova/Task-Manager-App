package com.anondo.taskmanager.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

        @Insert
        fun Add_Task(task : Task_Data_Class)

        @Update
        fun Edit_Task(alarm : Task_Data_Class)

        @Query("SELECT * FROM Task_Data_Class")
        fun Get_All_Task(): MutableList<Task_Data_Class>

        @Query("SELECT * FROM Task_Data_Class WHERE title LIKE '%' || :title || '%'")
        fun GetTaskByTitle(title: String): MutableList<Task_Data_Class>

        @Delete
        fun Delete_Task(alarm: Task_Data_Class)
    }