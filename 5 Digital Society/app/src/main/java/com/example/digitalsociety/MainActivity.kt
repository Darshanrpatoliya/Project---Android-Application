package com.example.digitalsociety

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var btnext=findViewById<ExtendedFloatingActionButton>(R.id.btn_next)
//
//        btnext.setOnClickListener {
//            Handler(Looper.getMainLooper()).postDelayed({
//
//                var i=Intent(this,OptionActivity2::class.java)
//                finish()
//                startActivity(i)
//            },1000)
//        }

        Handler(Looper.getMainLooper()).postDelayed({
                var i=Intent(this,OptionActivity2::class.java)
                finish()
                startActivity(i)
            },3000)

    }
}