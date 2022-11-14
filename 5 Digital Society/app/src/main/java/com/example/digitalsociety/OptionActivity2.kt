package com.example.digitalsociety

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.digitalsociety.chairman.Cm_login_Activity
import com.example.digitalsociety.members.SmRegistrationActivity

class OptionActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option2)

        var jump_cm=findViewById<AppCompatButton>(R.id.btn_jump_cm)
        var jump_sm=findViewById<AppCompatButton>(R.id.btn_jump_sm)

        jump_cm.setOnClickListener {
            var i1=Intent(this, Cm_login_Activity::class.java)
            startActivity(i1)
        }

        jump_sm.setOnClickListener {
            var i2=Intent(this, SmRegistrationActivity::class.java)
            startActivity(i2)
        }

    }
}