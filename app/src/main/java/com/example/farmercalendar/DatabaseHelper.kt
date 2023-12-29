package com.example.farmercalendar

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "garden.db"
        private const val DATABASE_VERSION = 1
    }

    init {
        // Create the database if it doesn't exist
        createDatabase()
    }

    private fun createDatabase() {
        val dbPath = context.getDatabasePath(DATABASE_NAME).absolutePath

        Log.d("DatabaseTest", "TRY")
        copyDatabase(dbPath)
//        if (!checkDatabaseExists(dbPath)) {
//            this.readableDatabase
//            copyDatabase(dbPath)
//        }
    }

    private fun checkDatabaseExists(dbPath: String): Boolean {
        val db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY)
        val exists = db != null
        db?.close()
        return exists
    }

    private fun copyDatabase(dbPath: String) {
        try {
            val inputStream: InputStream = context.assets.open(DATABASE_NAME)
            val outputStream: OutputStream = FileOutputStream(dbPath)

            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } > 0) {
                outputStream.write(buffer, 0, length)
            }

            outputStream.flush()
            outputStream.close()
            inputStream.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Database creation logic if needed
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Database upgrade logic if needed
    }

    data class Plant(
        val name: String,
        val description: String,
    )

//    fun getPlants(): List<Plant> {
//        val plants = mutableListOf<Plant>()
//
//        readableDatabase.use { db ->
//            // Specify the columns you want to retrieve
//            val columns = arrayOf("id", "name", "description")
//
//            // You can add conditions, sorting, etc., as needed
//            val cursor = db.query("plants", columns, null, null, null, null, null)
//
//            cursor.use {
//                while (cursor.moveToNext()) {
//                    val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
//                    val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
//                    val description = cursor.getString(cursor.getColumnIndexOrThrow("description"))
//
//                    val plant = Plant(id, name ?: "", description ?: "")
//                    plants.add(plant)
//                }
//            }
//        }
//
//        return plants
//    }
    fun getPlantByName(plantName: String): Plant? {
        val db = readableDatabase
        val columns = arrayOf("name", "description")

        val cursor = db.query("plants", columns, "name = ?", arrayOf(plantName), null, null, null)

        return cursor.use {
            if (it.moveToFirst()) {
                val name = it.getString(it.getColumnIndexOrThrow("name")) ?: ""
                val description = it.getString(it.getColumnIndexOrThrow("description")) ?: ""

                Plant(name, description)
            } else {
                null
            }
        }
    }

}
