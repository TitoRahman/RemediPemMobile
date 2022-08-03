package com.windi.remedipemmobile

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.windi.remedipemmobile.models.Todo
import java.text.SimpleDateFormat
import java.util.*

class AddNewEvent : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    var dd = 0
    var MM = 0
    var yy = 0
    var hh = 0
    var mm = 0

    var setdd = 0
    var setMM = 0
    var setyy = 0
    var sethh = 0
    var setmm = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_event)

        pickDate()

        findViewById<Button>(R.id.submit_button).setOnClickListener {
            val title = findViewById<TextView>(R.id.title_value).text
            val description = findViewById<TextView>(R.id.description_value).text
            val date = findViewById<TextView>(R.id.date_time_text).text
            if (title == "" || description == "" || date == "Set a Time First") {
                Toast.makeText(this, "Please fill all form!", Toast.LENGTH_SHORT).show()
            } else {
                val eventData = Todo(
                    title = "" + title,
                    description  = ""+description,
                    date = Date(setyy-1900, setMM-1, setdd, sethh, setmm)
                )
                val intent = Intent()
                setResult(
                    Activity.RESULT_OK,
                    intent.putExtra("Event",
                        eventData
                    )
                )
                finish()
            }
        }
    }

    private fun getDateTimeCalendar() {
        val calendar : Calendar = Calendar.getInstance()
        calendar.apply {
            dd = get(Calendar.DAY_OF_MONTH)
            MM = get(Calendar.MONTH)
            yy = get(Calendar.YEAR)
            hh = get(Calendar.HOUR)
            mm = get(Calendar.MINUTE)
        }
    }

    private fun pickDate(){
        val pickDateButton = findViewById<Button>(R.id.set_time_button)
        pickDateButton.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(this, this, yy, MM, dd).show()
        }
    }
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        setdd = p3
        setMM = p2
        setyy = p1

        getDateTimeCalendar()
        TimePickerDialog(this, this, hh, mm, true).show()
    }

    @SuppressLint("SimpleDateFormat")
    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        sethh = p1
        setmm = p2
        findViewById<TextView>(R.id.date_time_text).text = SimpleDateFormat("dd-MM-yyyy : hh:mm a").format(Date(setyy-1900, setMM-1, setdd, sethh, setmm )).toString()
    }
}