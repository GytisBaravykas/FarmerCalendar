package com.example.farmercalendar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farmercalendar.databinding.WikiFruitBinding

class WikiFruit : AppCompatActivity() {
    private lateinit var binding: WikiFruitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WikiFruitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.myToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.myToolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }


        binding.agrastas.setOnClickListener {
            val intent = Intent(this, InfoFruit::class.java)
            startActivity(intent)
        }

        binding.aronija.setOnClickListener {
            val intent = Intent(this, InfoFruit::class.java)
            startActivity(intent)
        }

    }
}