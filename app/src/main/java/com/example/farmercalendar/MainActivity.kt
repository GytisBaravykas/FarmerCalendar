package com.example.farmercalendar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.builders.DatePickerBuilder
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import com.example.farmercalendar.databinding.ActivityMainBinding
import com.example.farmercalendar.extensions.getDot
import java.util.Calendar


class MainActivity : AppCompatActivity(), OnDayClickListener, OnSelectDateListener {

    private lateinit var binding: ActivityMainBinding

    private val notes = mutableMapOf<EventDay, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabButton.setOnClickListener { openDatePicker() }
        binding.calendarView.setOnDayClickListener(this)

    }

    private fun openDatePicker() {
        DatePickerBuilder(this, this)
            .pickerType(CalendarView.ONE_DAY_PICKER)
            .headerColor(R.color.primary)
            .todayLabelColor(R.color.secondary)
            .selectionColor(R.color.secondary_light)
            .dialogButtonsColor(R.color.secondary)
            .build()
            .show()
    }

    override fun onDayClick(eventDay: EventDay) {
        val intent = Intent(this, NotePreviewActivity::class.java)
        intent.putExtra(CALENDAR_EXTRA, eventDay.calendar)
        intent.putExtra(NOTE_EXTRA, notes[eventDay])
        startActivity(intent)
    }

    override fun onSelect(calendar: List<Calendar>) {
        val intent = Intent(this, AddNoteActivity::class.java)
        intent.putExtra(CALENDAR_EXTRA, calendar.first())
        startActivityForResult(intent, RESULT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == RESULT_CODE) {
            val note = data?.getStringExtra(NOTE_EXTRA) ?: return
            val calendar = data.getSerializableExtra(CALENDAR_EXTRA) as Calendar
            val eventDay = EventDay(calendar, applicationContext.getDot())
            notes[eventDay] = note
            binding.calendarView.setEvents(notes.keys.toList())
        }
    }

    companion object {
        const val CALENDAR_EXTRA = "calendar"
        const val NOTE_EXTRA = "note"
        const val RESULT_CODE = 8
    }
}