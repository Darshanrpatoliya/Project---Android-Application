package com.example.digitalsociety.members.login

import android.annotation.SuppressLint
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
import com.example.digitalsociety.chairman.CMHomeScreenActivity
import com.example.digitalsociety.chairman.Cm_login_Activity
import com.example.digitalsociety.dataitem.SmDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SmLoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_sm_login, container, false)

        var tv_sm_name_email_error_login=view.findViewById<TextView>(R.id.tv_sm_name_email_error_login)
        var ed_sm_email_login=view.findViewById<AppCompatEditText>(R.id.ed_sm_email_login)
        var ed_sm_pass_login=view.findViewById<AppCompatEditText>(R.id.ed_sm_pass_login)
        var btn_show_hide_sm_login=view.findViewById<AppCompatButton>(R.id.btn_show_hide_sm_login)
        var btn_sm_login=view.findViewById<AppCompatButton>(R.id.btn_sm_login)

        //-----------TEMP TIME LOGIN
        var temp_sm_login=view.findViewById<TextView>(R.id.temp_sm_login)
        temp_sm_login.setOnClickListener {
            var il=Intent(view.context,WcSmOnlyActivity::class.java)
            startActivity(il)
        }

        btn_show_hide_sm_login.setOnClickListener {
            if (btn_show_hide_sm_login.text.toString()== "SHOW"){
                ed_sm_pass_login.transformationMethod= HideReturnsTransformationMethod.getInstance()
                ed_sm_pass_login.isEnabled=false
                btn_show_hide_sm_login.text="HIDE"
            }else{
                ed_sm_pass_login.transformationMethod= PasswordTransformationMethod.getInstance()
                ed_sm_pass_login.isEnabled=true
                btn_show_hide_sm_login.text="SHOW"
            }
        }

        var pragDialog= ProgressDialog(view.context)

        var retrofit=Retrofit.Builder().baseUrl("https://drpatoliya.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SmApiInterface::class.java)

        btn_sm_login.setOnClickListener {
            if (ed_sm_email_login.text!!.isEmpty() && ed_sm_pass_login.text!!.isEmpty()){
                tv_sm_name_email_error_login.setTextColor(resources.getColor(R.color.red))
                tv_sm_name_email_error_login.setText("Please Enter Full Your Email & Password")
            }
            else{
                pragDialog.setTitle("Login...")
                pragDialog.setMessage("Please Wait Until fewseconds...")
                pragDialog.show()

                var result=retrofit.loginSMembers(ed_sm_email_login.text.toString(),ed_sm_pass_login.text.toString())
                result.enqueue(object :Callback<List<SmDataItem>?>{
                    override fun onResponse(
                        call: Call<List<SmDataItem>?>,
                        response: Response<List<SmDataItem>?>
                    ) {
                        var responseBody=response.body()
                        for (data in responseBody!!){
                            var i1=Intent(view.context,WcSmOnlyActivity::class.java)
                            var id=data.id
                            i1.putExtra("smkeyid",id)
                            startActivity(i1)

                            Toast.makeText(view.context, "Login Succesfully ${data.fname}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<List<SmDataItem>?>, t: Throwable) {
                        Toast.makeText(view.context, "Fail login", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }

        return view
    }
}