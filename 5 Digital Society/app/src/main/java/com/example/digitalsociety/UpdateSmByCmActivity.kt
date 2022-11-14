package com.example.digitalsociety

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.digitalsociety.apiinterface.SmApiInterface
import com.example.digitalsociety.chairman.CMHomeScreenActivity
import com.example.digitalsociety.dataitem.SmDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpdateSmByCmActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_sm_by_cm)

        var ed_updt_sm_fname=findViewById<AppCompatEditText>(R.id.ed_updt_sm_fname)
        var ed_updt_sm_lname=findViewById<AppCompatEditText>(R.id.ed_updt_sm_lname)
        var ed_updt_sm_email=findViewById<AppCompatEditText>(R.id.ed_updt_sm_email)
        var ed_updt_mo_number=findViewById<AppCompatEditText>(R.id.ed_updt_mo_number)
        var ed_updt_fam_member=findViewById<AppCompatEditText>(R.id.ed_updt_fam_member)
        var ed_updt_occupation=findViewById<AppCompatEditText>(R.id.ed_updt_occupation)
        var ed_updt_flate_no=findViewById<AppCompatEditText>(R.id.ed_updt_flate_no)
        var btn_updt_update=findViewById<AppCompatButton>(R.id.btn_updt_update)

        var id=intent.getIntExtra("keyid",0)

        var retrofit=Retrofit.Builder().baseUrl("https://drpatoliya.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SmApiInterface::class.java)

        var pragDialog= ProgressDialog(this)


        btn_updt_update.setOnClickListener {
            if (ed_updt_sm_fname.text!!.isEmpty() && ed_updt_sm_lname.text!!.isEmpty() && ed_updt_sm_email.text!!.isEmpty()
                && ed_updt_mo_number.text!!.isEmpty() && ed_updt_fam_member.text!!.isEmpty()
                && ed_updt_occupation.text!!.isEmpty() && ed_updt_flate_no.text!!.isEmpty()){

                Toast.makeText(this, "Please Enter Full Details", Toast.LENGTH_SHORT).show()
            }
            else{
                pragDialog.setTitle("Update...")
                pragDialog.setMessage("Please Wait Updating Your Details...")
                pragDialog.show()

                var result=retrofit.updateSmDetails(id,ed_updt_sm_fname.text.toString(),ed_updt_sm_lname.text.toString(),
                    ed_updt_sm_email.text.toString(),ed_updt_mo_number.text.toString(),ed_updt_fam_member.text.toString(),
                    ed_updt_occupation.text.toString(),ed_updt_flate_no.text.toString())

                result.enqueue(object :Callback<SmDataItem?>{
                    override fun onResponse(call: Call<SmDataItem?>, response: Response<SmDataItem?>) {


                        var i=Intent(this@UpdateSmByCmActivity, CMHomeScreenActivity::class.java)
                        finish()
                        startActivity(i)
                    }

                    override fun onFailure(call: Call<SmDataItem?>, t: Throwable) {
                        Toast.makeText(this@UpdateSmByCmActivity, "update Failed", Toast.LENGTH_SHORT).show()
                    }

                })
            }

        }
    }
}