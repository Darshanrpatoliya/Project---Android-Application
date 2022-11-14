package com.example.a5note

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView

class MyAdepter(var context: Context,var notelist:MutableList<MyModel>):RecyclerView.Adapter<MyAdepter.MyClass>()
{
    class MyClass(itemview:View):RecyclerView.ViewHolder(itemview)
    {
        var title=itemview.findViewById<TextView>(R.id.tv_title)
        var note=itemview.findViewById<TextView>(R.id.tv_note)

        var btedit=itemview.findViewById<LinearLayout>(R.id.btn_layout)
        var btdelete=itemview.findViewById<TextView>(R.id.btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyClass {
        var v=LayoutInflater.from(parent.context).inflate(R.layout.myrow_layout,parent,false)
        return MyClass(v)
    }

    override fun onBindViewHolder(holder: MyClass, position: Int) {
        var my=notelist[position]

        holder.title.setText(my.mm_title)
        holder.note.setText(my.mm_notes)

        holder.btedit.setOnClickListener {
            var d=Dialog(context)
            d.setContentView(R.layout.myupdate_layout)
            d.show()

            var edtitle=d.findViewById<EditText>(R.id.ed_update_title)
            var ednote=d.findViewById<EditText>(R.id.ed_update_note)
            var btupdate=d.findViewById<AppCompatButton>(R.id.btn_update)

            edtitle.setText(my.mm_title)
            ednote.setText(my.mm_notes)

            btupdate.setOnClickListener {
                var mydb=MyDatabase(context)

                var mymodel=MyModel(my.mm_id,edtitle.text.toString(),ednote.text.toString())
                mydb.updateData(mymodel)

                var i=Intent(context,MainActivity::class.java)
                (context as Activity).finish()
                context.startActivity(i)
            }
        }

        holder.btdelete.setOnClickListener {

            var mydb=MyDatabase(context)

            var myModel=MyModel(my.mm_id,my.mm_title,my.mm_notes)
            mydb.deleteData(myModel)

            var i2=Intent(context,MainActivity::class.java)
            context.startActivity(i2)
        }
    }

    override fun getItemCount(): Int {
        return notelist.size
    }
}