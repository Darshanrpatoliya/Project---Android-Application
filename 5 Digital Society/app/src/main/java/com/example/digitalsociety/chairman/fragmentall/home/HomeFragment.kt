package com.example.digitalsociety.chairman.fragmentall.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalsociety.adepter.MyMembersAdepterOne
import com.example.digitalsociety.R
import com.example.digitalsociety.apiinterface.SmApiInterface
import com.example.digitalsociety.dataitem.SmDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)

        var view=inflater.inflate(R.layout.fragment_home, container, false)

        var btn_create_notice=view.findViewById<AppCompatButton>(R.id.btn_create_notice)

        btn_create_notice.setOnClickListener {
            var d=Dialog(view.context)
            d.setContentView(R.layout.notice_dialog_layout)

            var ed_title=d.findViewById<AppCompatEditText>(R.id.ed_title)
            var ed_note=d.findViewById<AppCompatEditText>(R.id.ed_note)
            var btn_post_notice=d.findViewById<AppCompatButton>(R.id.btn_post_notice)

            d.show()
        }

        return view
    }

}