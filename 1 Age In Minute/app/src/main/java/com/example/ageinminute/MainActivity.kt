package com.example.ageinminute

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btdate:AppCompatButton=findViewById(R.id.btn_date)

// when you click button for select date

        btdate.setOnClickListener {
            importdate()                         // function call for set a Date
        }
    }

//---------- fun defination --------------

    fun importdate(){
        var tvdate:TextView=findViewById(R.id.tv_selected)
        var tvminute:TextView=findViewById(R.id.tv_minute)
        var d=Calendar.getInstance()

        var d_dialog=DatePickerDialog(this@MainActivity,DatePickerDialog.OnDateSetListener
        { view, year, month, dayOfMonth ->
            var newmonth=month+1   // because month is strat from 0 for +1

            var date="$dayOfMonth/$newmonth/$year"
            tvdate.setText(date)                       // set date for you select date

// Convert Date into minute

            var sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            var thedate=sdf.parse(date)
            var dateintominute=thedate.time/60000
            var currentdate=sdf.parse(sdf.format(System.currentTimeMillis()))
            var cuurrentDateMinute=currentdate.time/60000
            var differenceinmin=cuurrentDateMinute-dateintominute
            tvminute.text=differenceinmin.toString()

        },d.get(Calendar.YEAR),d.get(Calendar.MONDAY),d.get(Calendar.DAY_OF_MONTH))

                d_dialog.datePicker.maxDate=System.currentTimeMillis()-1000  // aded -> for NO feauter date show
                        d_dialog.show()
        //.show()
    }
}