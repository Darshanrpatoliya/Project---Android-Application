package com.example.myquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)


        var tvName=findViewById<TextView>(R.id.tv_name)
        var tvScore=findViewById<TextView>(R.id.tv_score)
        var btfinish=findViewById<Button>(R.id.btn_finish)

        tvName.text=intent.getStringExtra(Constants.USER_NAME)

        var total_q=intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        var rightAns=intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        tvScore.setText("Your Score is $rightAns out of $total_q")

        btfinish.setOnClickListener {
            var i =Intent(this,MainActivity::class.java)
            finish()
            startActivity(i)
        }
    }
}