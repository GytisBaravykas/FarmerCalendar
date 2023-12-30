package com.example.farmercalendar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.builders.DatePickerBuilder
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import com.example.farmercalendar.databinding.CalendarActivityBinding
import com.example.farmercalendar.extensions.getDot
import java.util.Calendar


class CalendarActivity : AppCompatActivity(), OnDayClickListener, OnSelectDateListener {

    private lateinit var binding: CalendarActivityBinding
    private val notes = mutableMapOf<EventDay, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabButton.setOnClickListener { openDatePicker() }
        binding.calendarView.setOnDayClickListener(this)

        setupEvents()
        setupInfoViews()

        setSupportActionBar(binding.toolbar.myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.myToolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun setupEvents() {
        val events: MutableList<EventDay> = ArrayList()

        for (x in 0..31) {
            val calendar = Calendar.getInstance().apply {
                set(Calendar.YEAR, 2023)
                set(Calendar.MONTH, Calendar.DECEMBER)
                set(Calendar.DAY_OF_MONTH, x)
            }
            events.add(EventDay(calendar, if (x < 10) R.drawable.two_garden_tree else R.drawable.two_garden_flower))
        }

        binding.calendarView.setEvents(events)
    }

    private fun setupInfoViews() {
        val inflater = LayoutInflater.from(this)
        val infoView1 = generateInfoView(inflater, R.drawable.garden, "Naminių gėlių sodinimas", "2023-12-01 - 2023-12-10")
        val infoView2 = generateInfoView(inflater, R.drawable.flower, "Gėlių apklojimas sniegu", "2023-12-10 - 2023-12-31")

        binding.grid.addView(infoView1)
        binding.grid.addView(infoView2)
    }

    private fun generateInfoView(inflater: LayoutInflater, imageResource: Int, job: String, dates: String): View {
        val info = inflater.inflate(R.layout.calendar_info, binding.grid, false)

        val itemImageView: ImageView = info.findViewById(R.id.itemImageView)
        val itemTextView1: TextView = info.findViewById(R.id.itemTextView1)
        val itemTextView2: TextView = info.findViewById(R.id.itemTextView2)

        itemImageView.setImageResource(imageResource)
        itemTextView1.text = job
        itemTextView2.text = dates

        return info
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