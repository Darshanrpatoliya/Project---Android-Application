package com.example.digitalsociety.chairman

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.digitalsociety.R
import com.example.digitalsociety.apiinterface.CmApiInterface
import com.example.digitalsociety.dataitem.CmDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Cm_login_Activity : AppCompatActivity() {

    //lateinit var cmname:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cm_login)

//-------------------------Temp -------------------------------------------
        var temp_cm_login=findViewById<TextView>(R.id.temp_cm_login)
        temp_cm_login.setOnClickListener {
            var i=Intent(this@Cm_login_Activity, CMHomeScreenActivity::class.java)
            finish()
            startActivity(i)
        }
//-------------------------Temp -------------------------------------------
        var pragDialog=ProgressDialog(this@Cm_login_Activity)

        var edcm_email=findViewById<AppCompatEditText>(R.id.ed_cm_email)
        var edcm_pass=findViewById<AppCompatEditText>(R.id.ed_cm_pass)
        var btncm_login=findViewById<AppCompatButton>(R.id.btn_cm_login)
        var tverror=findViewById<TextView>(R.id.tv_name_error)
        var btshow_hide=findViewById<AppCompatButton>(R.id.btn_show_hide)

        btshow_hide.setOnClickListener {
            if (btshow_hide.text.toString()== "SHOW"){
                edcm_pass.transformationMethod=HideReturnsTransformationMethod.getInstance()
                edcm_pass.isEnabled=false
                btshow_hide.text="HIDE"
            }else{
                edcm_pass.transformationMethod=PasswordTransformationMethod.getInstance()
                edcm_pass.isEnabled=true
                btshow_hide.text="SHOW"
            }
        }

        var retrofit=Retrofit.Builder().baseUrl("https://drpatoliya.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CmApiInterface::class.java)

        btncm_login.setOnClickListener {
            if (edcm_email.text!!.isEmpty() && edcm_pass.text!!.isEmpty()){
                tverror.setTextColor(resources.getColor(R.color.red))
            }
            else{
                pragDialog.setTitle("Login...")
                pragDialog.setMessage("Please Wait Until fewseconds...")
                pragDialog.show()

                var result=retrofit.loginCM(edcm_email.text.toString(),edcm_pass.text.toString())

                result.enqueue(object :Callback<List<CmDataItem>?>{
                    override fun onResponse(
                        call: Call<List<CmDataItem>?>,
                        response: Response<List<CmDataItem>?>
                    ) {
                        var responseBody=response.body()
                        for (data in responseBody!!){
                            var i=Intent(this@Cm_login_Activity, CMHomeScreenActivity::class.java)
                            finish()
                            startActivity(i)
                        }
                    }

                    override fun onFailure(call: Call<List<CmDataItem>?>, t: Throwable) {
                        tverror.setText("Enter Valid Email & Password")
                        tverror.setTextColor(resources.getColor(R.color.red))
                        pragDialog.cancel()
                    }

                })
            }
        }

    }
}