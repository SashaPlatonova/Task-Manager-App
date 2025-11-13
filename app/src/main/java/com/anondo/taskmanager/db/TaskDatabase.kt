package com.anondo.taskmanager.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Task_Data_Class::class] , version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun addTaskDao() : TaskDao

}