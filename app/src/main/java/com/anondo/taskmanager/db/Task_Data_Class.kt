package com.anondo.taskmanager.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Task_Data_Class(
    @PrimaryKey(autoGenerate = true)
   var id : Int ,
   var title : String ,
   var description : String ,
   var duedate : String ,
   var status : Boolean
) : Parcelable