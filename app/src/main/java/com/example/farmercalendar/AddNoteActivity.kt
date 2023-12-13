package com.example.farmercalendar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farmercalendar.MainActivity.Companion.CALENDAR_EXTRA
import com.example.farmercalendar.MainActivity.Companion.NOTE_EXTRA
import com.example.farmercalendar.databinding.ActivityAddNoteBinding
import com.example.farmercalendar.extensions.toSimpleDate
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendar = intent.getSerializableExtra(CALENDAR_EXTRA) as Calendar
        binding.toolbar.subtitle = calendar.time.toSimpleDate()

        binding.saveButton.setOnClickListener {
            val note = binding.noteEditText.text.toString()
            if (note.isNotEmpty()) {
                val returnIntent = Intent()
                returnIntent.putExtra(CALENDAR_EXTRA, calendar)
                returnIntent.putExtra(NOTE_EXTRA, note)
                setResult(RESULT_OK, returnIntent)
            }
            finish()
        }
    }
}