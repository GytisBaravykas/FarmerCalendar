package com.example.farmercalendar

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.farmercalendar.databinding.ActivityMainBinding
import com.example.farmercalendar.databinding.WikiDesignBinding
import java.util.Objects

class WikiFirst : AppCompatActivity() {
    private lateinit var binding: WikiDesignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WikiDesignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.myToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.myToolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }


        


    }
}