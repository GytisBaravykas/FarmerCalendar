package com.example.farmercalendar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.farmercalendar.databinding.IntoFruitBinding

class InfoFruit : AppCompatActivity() {
    private lateinit var binding: IntoFruitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IntoFruitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.myToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.myToolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val name = intent.getStringExtra("EXTRA_MESSAGE")

        val plant = DatabaseHelper(this).getPlantByName(name.toString())

        if (plant != null) {
            binding.titleWiki.text = plant.name
            binding.textWiki.text = plant.description
        }
    }
}