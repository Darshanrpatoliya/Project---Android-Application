package com.example.myquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity()
{
    lateinit var btstart:AppCompatButton
    lateinit var edname:AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var btstart=findViewById<AppCompatButton>(R.id.btn_start)
//        var edname=findViewById<AppCompatEditText>(R.id.ed_name)
        var tverror=findViewById<TextView>(R.id.tv_name_error)

        btstart=findViewById(R.id.btn_start)
        edname=findViewById(R.id.ed_name)

        btstart.setOnClickListener {

            if(edname.text!!.isEmpty()){
                Toast.makeText(this, "Please Fill Name", Toast.LENGTH_SHORT).show()
                tverror.setTextColor(resources.getColor(R.color.red))
            }
            else{
                var i=Intent(this,QuizQuestionActivity1::class.java)
                i.putExtra(Constants.USER_NAME,edname.text.toString())
                finish()
                startActivity(i)
            }
        }

    }
}