package com.example.ageinmintues

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selected : TextView? = null
    private var min : TextView?=null
    private var hr : TextView?=null
    private var week: TextView?=null
    private var sec : TextView?=null
    private var age : TextView?=null
    private var months : TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selected= findViewById(R.id.date_view)
        hr= findViewById(R.id.hr_views)
        val btn: Button= findViewById(R.id.date)
        min= findViewById(R.id.min_views)
        week = findViewById(R.id.week)
        sec = findViewById(R.id.sec)
        age= findViewById(R.id.age)
        months =findViewById(R.id.months)
        btn.setOnClickListener {

            getDate()
        }
    }
    private fun getDate(){
        val myCal= Calendar.getInstance()
        val year= myCal.get(Calendar.YEAR)
        val month= myCal.get(Calendar.MONTH)
        val day= myCal.get(Calendar.DAY_OF_MONTH)
        val data=DatePickerDialog( this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val selected_date= "$day/${month+1}/$year"
            selected?.text= selected_date
            val sdf= SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate= sdf.parse(selected_date)
            theDate?.let {
                val selectedDateInsec= theDate.time / 1000
                val selectedDateInMin= theDate.time /  60000
                val selectedDateInhr= theDate.time / 3600000
                val selectedDateInweek = theDate.time / 604800000
                val year_val= year
                val selectedDateInmonths= theDate.time / 2629746000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentTimeInsec= currentDate.time / 1000
                    val currentTimeInmin= currentDate.time / 60000
                    val currentTimeInhr = currentDate.time / 3600000
                    val currentTimeInweek = currentDate.time / 604800000.0
                    val currentTimeInmonths =currentDate.time / 2629746000.0
                    val current_year = myCal.get(Calendar.YEAR)
                    var actual_age = current_year - year_val
                    val actual_month = currentTimeInmonths - selectedDateInmonths
                    var actual_min= currentTimeInmin - selectedDateInMin
                    var actual_hr = currentTimeInhr -selectedDateInhr
                    val actual_week =currentTimeInweek - selectedDateInweek
                    var actual_sec =currentTimeInsec - selectedDateInsec
                    var actual_month_val =roundOffDecimal(actual_month).toString()
                    var actual_sec_val = actual_sec.toString()
                    actual_month_val= actual_month_val+" months"
                    actual_sec_val =actual_sec_val+" sec"
                    var actual_week_val= roundOffDecimal(actual_week).toString()
                    var actual_hr_val= actual_hr.toString()
                    var actual_date_val= actual_min.toString()
                    actual_date_val= actual_date_val+" min"
                    actual_hr_val =actual_hr_val+" hr"
                    actual_week_val= actual_week_val+" weeks"
                    age?.text= actual_age.toString()
                    months?.text=actual_month_val
                    week?.text=actual_week_val
                    hr?.text=actual_hr_val
                    min?.text=actual_date_val
                    sec?.text=actual_sec_val
                }

            }

        },
        year,
        month,
        day
        )
        data.datePicker.maxDate= System.currentTimeMillis() - 86400000
        data.show()
    }
    fun roundOffDecimal(number: Double): Double? {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).toDouble()
    }



}