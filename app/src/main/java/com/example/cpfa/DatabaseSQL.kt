package com.example.cpfa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.SQLTimeoutException
import kotlin.concurrent.thread

class DatabaseSQL{
    var connection: Connection? = null

    fun getString(memes : String) {
        thread {
            val p = "5432"

            try {
                connection = DriverManager.getConnection(
                    "jdbc:postgresql://${"ec2-54-72-136-69.eu-west-1.compute.amazonaws.com"}:${p}/${"d5lok6g2a31a1a"}",
                    "yjmdkfztpfadce",
                   "072264bda3d6b3f3ddfd0d5e605f53423a5feb74f3f80abf571769211e71e4da"
                )

            } catch (e: SQLException) {


                Log.e(this::class.toString(), e.message, e)
            } catch (e: SQLTimeoutException) {

                Log.e(this::class.toString(), e.message, e)
            }
            onClickSelect(memes)
        }
    }

    fun onClickSelect(memes : String) {
        if (connection == null || connection?.isClosed == true) {

            return
        }

        thread {
            try {
                connection!!.createStatement().use { s ->
                    s.executeQuery("SELECT Mem FROM Memes WHERE Id = $memes ").use {
                        var r = ""

                        while (it.next()) {
                            //val id = it.getInt("Id")
                            val name = it.getString("Mem")
                            System.out.println(name)
                            //r += "${id}: ${name}\n"
                        }


                    }
                }
            } catch (e: SQLException) {
                Log.e(this::class.toString(), e.message, e)
            }
        }
    }
}
