package com.example.digitalsociety.members.registration

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.digitalsociety.OptionActivity2
import com.example.digitalsociety.R
import com.example.digitalsociety.apiinterface.SmApiInterface
import com.example.digitalsociety.dataitem.SmDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SmRegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sm_registration, container, false)
        val view=inflater.inflate(R.layout.fragment_sm_registration, container, false)

        var tv_sm_error=view.findViewById<TextView>(R.id.tv_sm_error)
        var ed_sm_fname=view.findViewById<AppCompatEditText>(R.id.ed_sm_fname)
        var ed_sm_lname=view.findViewById<AppCompatEditText>(R.id.ed_sm_lname)
        var ed_sm_email=view.findViewById<AppCompatEditText>(R.id.ed_sm_email)
        var ed_mo_number=view.findViewById<AppCompatEditText>(R.id.ed_mo_number)
        var ed_fam_member=view.findViewById<AppCompatEditText>(R.id.ed_fam_member)
        var ed_occupation=view.findViewById<AppCompatEditText>(R.id.ed_occupation)
        var ed_flate_no=view.findViewById<AppCompatEditText>(R.id.ed_flate_no)
        var ed_sm_pass=view.findViewById<AppCompatEditText>(R.id.ed_sm_pass)
        var btn_show_hide_sm_reg=view.findViewById<AppCompatButton>(R.id.btn_show_hide_sm_reg)
        var btn_signup=view.findViewById<AppCompatButton>(R.id.btn_signup)

        btn_show_hide_sm_reg.setOnClickListener {
            if (btn_show_hide_sm_reg.text.toString() == "SHOW"){
                ed_sm_pass.transformationMethod= HideReturnsTransformationMethod.getInstance()
                ed_sm_pass.isEnabled=false
                btn_show_hide_sm_reg.text="HIDE"
            }else{
                ed_sm_pass.transformationMethod= PasswordTransformationMethod.getInstance()
                ed_sm_pass.isEnabled=true
                btn_show_hide_sm_reg.text="SHOW"
            }
        }

        var retrofit=Retrofit.Builder().baseUrl("https://drpatoliya.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SmApiInterface::class.java)

        var pragDialog= ProgressDialog(view.context)

        btn_signup.setOnClickListener {
            if (ed_sm_fname.text!!.isEmpty() && ed_sm_lname.text!!.isEmpty() && ed_sm_email.text!!.isEmpty() &&
                ed_mo_number.text!!.isEmpty() && ed_fam_member.text!!.isEmpty() && ed_occupation.text!!.isEmpty() &&
                ed_flate_no.text!!.isEmpty() && ed_sm_pass.text!!.isEmpty()){
                tv_sm_error.setTextColor(resources.getColor(R.color.red))
            }else{

                pragDialog.setTitle("Registration...")
                pragDialog.setMessage("Please Wait Sending Your Details...")
                pragDialog.show()

                var result=retrofit.insertRegistrationData(ed_sm_fname.text.toString(),ed_sm_lname.text.toString(),ed_sm_email.text.toString(),
                ed_mo_number.text.toString(),ed_fam_member.text.toString(),ed_occupation.text.toString(),ed_flate_no.text.toString(),ed_sm_pass.text.toString())

                result.enqueue(object :Callback<SmDataItem?>{
                    override fun onResponse(
                        call: Call<SmDataItem?>,
                        response: Response<SmDataItem?>
                    ) {
                        Toast.makeText(view.context, " Registration Successfully ", Toast.LENGTH_SHORT).show()
                        var i1=Intent(view.context, OptionActivity2::class.java)
                        activity?.finish()
                        startActivity(i1)
                    }

                    override fun onFailure(call: Call<SmDataItem?>, t: Throwable) {
                        Toast.makeText(view.context, "Sorry Try Again!!", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }

        return view
    }

}