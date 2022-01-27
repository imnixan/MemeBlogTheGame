package com.example.MemBlogGame

import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.SQLTimeoutException


class DatabaseSQL{
    var connection: Connection? = null

    fun getConnect(){
            System.out.println("Starting getString");
            val p = "5432"

            try {
                connection = DriverManager.getConnection(
                    "jdbc:postgresql://${"ec2-54-72-136-69.eu-west-1.compute.amazonaws.com"}:${p}/${"d5lok6g2a31a1a"}",
                    "yjmdkfztpfadce",
                   "072264bda3d6b3f3ddfd0d5e605f53423a5feb74f3f80abf571769211e71e4da"
                )

            } catch (e: SQLException) {
                System.out.println("error sql ex " + e);


                Log.e(this::class.toString(), e.message, e)
            } catch (e: SQLTimeoutException) {
                System.out.println("error sql time ex " + e);

                Log.e(this::class.toString(), e.message, e)
            }



    }

    fun getSelect(memes : String) : String {

        if(memes.equals("count")){
            return getCount()
        }

        var name = ""
        if (connection == null || connection?.isClosed == true) {

            return ""
        }


            try {
                connection!!.createStatement().use { s ->
                    s.executeQuery("SELECT Mem FROM Memes WHERE Id = $memes ").use {

                        while (it.next()) {
                            name = it.getString("Mem")


                       }


                    }
                }
            } catch (e: SQLException) {
                Log.e(this::class.toString(), e.message, e)
            }

    return name.toString()
    }




    fun getCount() : String {


        var name = ""
        if (connection == null || connection?.isClosed == true) {

            return ""
        }


        try {
            connection!!.createStatement().use { s ->
                s.executeQuery("SELECT COUNT (*) FROM Memes").use {

                    while (it.next()) {
                        name = it.getString("count")


                    }


                }
            }
        } catch (e: SQLException) {
            Log.e(this::class.toString(), e.message, e)
        }

        return name.toString()
    }

}
