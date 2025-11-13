package com.anondo.taskmanager.reducecode

import android.content.Context
import android.icu.util.Calendar
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.anondo.taskmanager.db.TaskDao
import com.anondo.taskmanager.db.TaskDatabase
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object EncryptDecrypt {

    fun encryptData(plainText : String) : String{


        var plainTextByte = plainText.toByteArray(Charsets.UTF_8)

        var password = "7fQ@Lp2!vC9#rXen"
        var passwordByte = password.toByteArray(Charsets.UTF_8)

        var secretKey = SecretKeySpec(passwordByte, "AES")

        var ciper : Cipher = Cipher.getInstance("AES")
        ciper.init(Cipher.ENCRYPT_MODE , secretKey)
        var finalByte = ciper.doFinal(plainTextByte)

        var encodStr = Base64.encodeToString(finalByte , Base64.DEFAULT)

        return encodStr
    }

    fun decryptData(encData : String) : String{

        var decodeData = Base64.decode(encData , Base64.DEFAULT)

        var password = "7fQ@Lp2!vC9#rXen"
        var passwordByte = password.toByteArray(Charsets.UTF_8)

        var secretKey = SecretKeySpec(passwordByte, "AES")

        var ciper : Cipher = Cipher.getInstance("AES")
        ciper.init(Cipher.DECRYPT_MODE , secretKey)
        var finalByte = ciper.doFinal(decodeData)

        var plainDatas = String(finalByte , Charsets.UTF_8)

        return plainDatas
    }

    fun database(context: Context): TaskDao {

        var db = Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "Task_Data"
        ).allowMainThreadQueries().build()

        var dao = db.addTaskDao()

        return dao
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun currentTime(): String {
        val currentDate =LocalDate.now()
        val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        return formattedDate
    }


}