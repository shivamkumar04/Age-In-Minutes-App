package android.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val selectdatebutton: Button=findViewById(R.id.btnDatePicker)
        selectdatebutton.setOnClickListener{view ->
            clickDatePicker(view)

        }


    }
    fun clickDatePicker(view: View){

        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

      val dpd=DatePickerDialog(this,
          DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
              Toast.makeText(this, "the chosen year is $selectedYear, the month is $selectedMonth,the day is $selectedDayOfMonth", Toast.LENGTH_LONG).show()
              val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

              val tvsd: TextView=findViewById(R.id.tvselectdate)
              tvsd.text = selectedDate
              val sdf= SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

              val theDate=sdf.parse(selectedDate)
              val selectedDateToMinutes = theDate!!.time / 60000

              val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

              val currentDateToMinutes = currentDate!!.time / 60000


              val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
              val tvsda: TextView=findViewById(R.id.tvselectdateinminutes)
              tvsda.text = differenceInMinutes.toString()
          },year,month,day)
        // 86400000 is milliseconds of 24 Hours. Which is used to restrict the user to select today and future day.
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show() // It is used to show the datePicker Dialog.show()
    }

}