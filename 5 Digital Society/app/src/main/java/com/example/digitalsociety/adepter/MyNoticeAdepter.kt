package com.example.digitalsociety.adepter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalsociety.R
import com.example.digitalsociety.dataitem.NoticeDataItem

class MyNoticeAdepter(var context: Context,var mynoticelist:List<NoticeDataItem>):RecyclerView.Adapter<MyNoticeAdepter.MyClass>()
{
    class MyClass(itemview:View):RecyclerView.ViewHolder(itemview){
        var tv_sm_title=itemview.findViewById<TextView>(R.id.tv_sm_title)
        var tv_sm_note=itemview.findViewById<TextView>(R.id.tv_sm_note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyClass {
        var v2=LayoutInflater.from(parent.context).inflate(R.layout.notice_one_row_layout,parent,false)
        return MyClass(v2)
    }

    override fun onBindViewHolder(holder: MyClass, position: Int) {
        var my2=mynoticelist[position]
        holder.tv_sm_title.setText(my2.title)
        holder.tv_sm_note.setText(my2.note)
    }

    override fun getItemCount(): Int {
        return mynoticelist.size
    }
}