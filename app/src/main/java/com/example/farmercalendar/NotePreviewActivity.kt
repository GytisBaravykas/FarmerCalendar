package com.example.farmercalendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.farmercalendar.CalendarActivity.Companion.CALENDAR_EXTRA
import com.example.farmercalendar.CalendarActivity.Companion.NOTE_EXTRA
import com.example.farmercalendar.databinding.ActivityNotePreviewBinding
import com.example.farmercalendar.extensions.toSimpleDate
import java.util.*

class NotePreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotePreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotePreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendar = intent.getSerializableExtra(CALENDAR_EXTRA) as Calendar
        binding.toolbar.subtitle = calendar.time.toSimpleDate()

        val note = intent.getStringExtra(NOTE_EXTRA)

        if (note != null) {
            binding.noteTextView.text = note
            binding.emptyStateTextView.isVisible = false
        }
    }
}