package com.example.a5note

import android.content.Intent
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteSampleActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_sample2)

        var edtitle=findViewById<EditText>(R.id.ed_title)
        var ednote=findViewById<EditText>(R.id.ed_note)
        var btsave=findViewById<FloatingActionButton>(R.id.btn_save)

        var id:Int
        var mydatabase=MyDatabase(this)

        btsave.setOnClickListener {
            var insertId=mydatabase.insertData(MyModel(it.id!!,edtitle.text.toString(),ednote.text.toString()))

            if (insertId>0)
            {
                Toast.makeText(this, "Note Succesfully Save", Toast.LENGTH_SHORT).show()

                var i=Intent(this,MainActivity::class.java)
                startActivity(i)
            }
        }

        var btback=findViewById<AppCompatButton>(R.id.btn_back)

        btback.setOnClickListener {
            var i2=Intent(this,MainActivity::class.java)
            finish()
            startActivity(i2)
        }

    }
}