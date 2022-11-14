package com.example.a5note

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var btaddnote:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btaddnote=findViewById(R.id.btn_add_note)

        btaddnote.setOnClickListener {
            var i=Intent(this,NoteSampleActivity2::class.java)
            startActivity(i)
        }

        var recyclerView=findViewById<RecyclerView>(R.id.rec_getdata)

        recyclerView.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        var mydb=MyDatabase(this)

        var notelist:MutableList<MyModel> = ArrayList()
        notelist=mydb.getall_data()

        var adp=MyAdepter(this@MainActivity,notelist)
        recyclerView.adapter=adp

    }
}