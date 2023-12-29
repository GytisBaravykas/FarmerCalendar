package com.example.farmercalendar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.farmercalendar.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DatabaseHelper(this).readableDatabase

        binding.openCalendarButton.setOnClickListener {
            val intent = Intent(
                this,
                CalendarActivity::class.java
            )
            startActivity(intent)
        }
        binding.wiki.setOnClickListener {
            startActivity(Intent(this, WikiFirst::class.java))
        }

    }
}