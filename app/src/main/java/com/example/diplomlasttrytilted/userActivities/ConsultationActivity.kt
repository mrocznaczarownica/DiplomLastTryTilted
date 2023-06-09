package com.example.diplomlasttrytilted.userActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.TimePicker
import android.widget.Toast
import com.example.diplomlasttrytilted.R
import java.text.SimpleDateFormat
import java.util.*

class ConsultationActivity : AppCompatActivity() {

    lateinit var firstName:EditText
    lateinit var name:EditText
    lateinit var lastName:EditText
    lateinit var datePicker: DatePicker
    lateinit var phone:EditText
    lateinit var dateSelect:String
    lateinit var spinner: Spinner
    lateinit var timePicker: TimePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultation)

        firstName = findViewById(R.id.firstName)
        name = findViewById(R.id.name)
        lastName = findViewById(R.id.lastName)
        datePicker = findViewById(R.id.datePicker)
        phone = findViewById(R.id.phone)
        timePicker = findViewById(R.id.timeSelector)

        var today = Calendar.getInstance()
        val month=getAbbreviatedFromDateTime(today,"MM"); //преобразовать месяц и день в нужный формат
        val day=getAbbreviatedFromDateTime(today,"dd");
        dateSelect = "${today.get(Calendar.YEAR)}-${month}-${day}" //сохранить в переменную полученную дату

        datePicker.minDate = today.getTimeInMillis()  //ограничить выбор даты
        //нельзя выбрать прошедшую
        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            val month = month + 1;
            dateSelect = "${year}-${month}-${day}"
        }

        timePicker.setIs24HourView(true)
        timePicker.hour = 8
        timePicker.minute = 0
        timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            if (hourOfDay < 8 || hourOfDay >= 18) {
                timePicker.hour = 8
                timePicker.minute = 0
            }
        }

        var lastTime: Calendar? = null
        val currentTime = Calendar.getInstance()
        if (lastTime != null) {
            val diff = (currentTime.timeInMillis - lastTime.timeInMillis) / (1000 * 60 * 60)
            if (diff < 1.5) {
                Toast.makeText(this, "Разница между консультациями менее 1.5 часов", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun consuttationButtonClick(view: View) {
        if(firstName.text.toString().isNotEmpty() && name.text.toString().isNotEmpty() && lastName.text.toString().isNotEmpty() && datePicker.toString().isNotEmpty() && phone.text.toString().isNotEmpty()){
            Toast.makeText(this, "Запись на консультацию выполнена", Toast.LENGTH_SHORT).show()
            firstName.setText("")
            name.setText("")
            lastName.setText("")
            phone.setText("")
        } else {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }

    //формат даты
    fun getAbbreviatedFromDateTime(dateTime: Calendar, field: String): String? {
        val output = SimpleDateFormat(field)
        try {
            return output.format(dateTime.time)    // format output
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}