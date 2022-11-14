package com.example.digitalsociety.adepter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalsociety.InDetailsSmViewByCmActivity
import com.example.digitalsociety.R
import com.example.digitalsociety.dataitem.SmDataItem

class MyMembersAdepterOne(var context: Context,var mylistmembers:List<SmDataItem>):RecyclerView.Adapter<MyMembersAdepterOne.MyClass>()
{
    class MyClass(itemview:View):RecyclerView.ViewHolder(itemview)
    {
        var tvname=itemview.findViewById<TextView>(R.id.tv_list_of_mem_fullname)
        var tvf_nummber=itemview.findViewById<TextView>(R.id.tv_list_of_mem_f_number)
        var tvmo_num=itemview.findViewById<TextView>(R.id.tv_list_of_mem_monumber)
        var tvemail=itemview.findViewById<TextView>(R.id.tv_list_of_mem_email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyClass {
        var v=LayoutInflater.from(parent.context).inflate(R.layout.my_row_layout_one,parent,false)
        return MyClass(v)
    }

    override fun onBindViewHolder(holder: MyClass, position: Int) {
        var my=mylistmembers[position]
        holder.tvname.setText("${my.fname} ${my.lname}")
        holder.tvf_nummber.setText(my.flate_number)
        holder.tvmo_num.setText(my.mo_number)
        holder.tvemail.setText(my.email)

        holder.itemView.setOnClickListener {
            var i1=Intent(context, InDetailsSmViewByCmActivity::class.java)
            var id=my.id
            i1.putExtra("keyid",id)
            context.startActivity(i1)
        }
    }

    override fun getItemCount(): Int {
        return mylistmembers.size
    }
}