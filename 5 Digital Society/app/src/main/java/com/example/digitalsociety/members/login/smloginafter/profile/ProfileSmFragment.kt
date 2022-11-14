package com.example.digitalsociety.members.login.smloginafter.profile

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.digitalsociety.R
import com.example.digitalsociety.apiinterface.SmApiInterface
import com.example.digitalsociety.dataitem.SmDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProfileSmFragment : Fragment() {

//    private var id=Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_profile_sm, container, false)

        var tv_Sm_fullname=view.findViewById<TextView>(R.id.tv_Sm_fullname)
        var tv_SM_email=view.findViewById<TextView>(R.id.tv_SM_email)
        var tv_SM_Mo_number=view.findViewById<TextView>(R.id.tv_SM_Mo_number)
        var tv_SM_Fam_Members=view.findViewById<TextView>(R.id.tv_SM_Fam_Members)
        var tv_SM_Occupation=view.findViewById<TextView>(R.id.tv_SM_Occupation)
        var tv_SM_Flate_no=view.findViewById<TextView>(R.id.tv_SM_Flate_no)

        var pragDialog= ProgressDialog(view.context)
        pragDialog.setTitle("Fatching...")
        pragDialog.setMessage("Please Wait Your Details is Redy....")
        pragDialog.show()

        // smkeyid
        // var id=intent.getIntExtra("keyid",0)
//        var bundle = arguments
//        id=bundle.getInt("smtkeyid",0)

//        var bundle = arguments
//        var id = bundle!!.getInt("smkeyid")
        
//
        var retrofit= Retrofit.Builder().baseUrl("https://drpatoliya.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SmApiInterface::class.java)
//
        var result=retrofit.get_spec_sm(id)
        result.enqueue(object :Callback<List<SmDataItem>?>{
            override fun onResponse(
                call: Call<List<SmDataItem>?>,
                response: Response<List<SmDataItem>?>
            ) {
                var responseBody=response.body()
                for (data in responseBody!!){
                    tv_Sm_fullname.setText("${data.fname} ${data.lname}")
                    tv_SM_email.setText("Email : ${data.email}")
                    tv_SM_Mo_number.setText("Phone Number : ${data.mo_number}")
                    tv_SM_Fam_Members.setText("Family Members : ${data.f_member}")
                    tv_SM_Occupation.setText("Occupation : ${data.occupation}")
                    tv_SM_Flate_no.setText("House No. : ${data.flate_number}")

                    pragDialog.dismiss()
                }
            }

            override fun onFailure(call: Call<List<SmDataItem>?>, t: Throwable) {
                Toast.makeText(view.context, "Failed", Toast.LENGTH_SHORT).show()
            }

        })

        return view
    }

}