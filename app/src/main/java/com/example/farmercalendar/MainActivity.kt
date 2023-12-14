package com.example.farmercalendar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farmercalendar.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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