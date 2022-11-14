package com.example.digitalsociety.members.login.smloginafter.notice

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalsociety.R
import com.example.digitalsociety.adepter.MyNoticeAdepter
import com.example.digitalsociety.apiinterface.SmApiInterface
import com.example.digitalsociety.dataitem.NoticeDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NoticeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_notice2, container, false)

        var pragDialog= ProgressDialog(view.context)
        pragDialog.setTitle("Fatching Notice...")
        pragDialog.setMessage("Please Wait Notice is Redy....")
        pragDialog.show()

        var recyclerView=view.findViewById<RecyclerView>(R.id.rec_sm_display_notice)
        recyclerView.layoutManager=LinearLayoutManager(view.context)

        var retrofit= Retrofit.Builder().baseUrl("https://drpatoliya.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SmApiInterface::class.java)

        var result=retrofit.get_Notice()
        result.enqueue(object : Callback<List<NoticeDataItem>?>{
            override fun onResponse(
                call: Call<List<NoticeDataItem>?>,
                response: Response<List<NoticeDataItem>?>
            ) {
                var responseBody=response.body() as List<NoticeDataItem>
                var adp=MyNoticeAdepter(view.context,responseBody)
                recyclerView.adapter=adp

                pragDialog.dismiss()
            }

            override fun onFailure(call: Call<List<NoticeDataItem>?>, t: Throwable) {
                Toast.makeText(view.context, "Failed", Toast.LENGTH_SHORT).show()
                pragDialog.dismiss()
            }

        })

        return view
    }

}