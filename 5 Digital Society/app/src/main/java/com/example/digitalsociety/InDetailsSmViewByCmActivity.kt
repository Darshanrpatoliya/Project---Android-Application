package com.example.digitalsociety

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.digitalsociety.apiinterface.SmApiInterface
import com.example.digitalsociety.chairman.CMHomeScreenActivity
import com.example.digitalsociety.dataitem.SmDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InDetailsSmViewByCmActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_details_sm_view_by_cm)

        var tv_EDfullname=findViewById<TextView>(R.id.tv_ED_fullname)
        var tv_EDemail=findViewById<TextView>(R.id.tv_ED_email)
        var tv_EDMo_number=findViewById<TextView>(R.id.tv_ED_Mo_number)
        var tv_EDFam_Members=findViewById<TextView>(R.id.tv_ED_Fam_Members)
        var tv_EDOccupation=findViewById<TextView>(R.id.tv_ED_Occupation)
        var tv_EDFlate_no=findViewById<TextView>(R.id.tv_ED_Flate_no)

        var pragDialog= ProgressDialog(this)
        pragDialog.setTitle("Wait...")
        pragDialog.setMessage("Please Wait Data is Redy....")
        pragDialog.show()

        var id=intent.getIntExtra("keyid",0)

        var retrofit=Retrofit.Builder().baseUrl("https://drpatoliya.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SmApiInterface::class.java)

        var result=retrofit.get_spec_sm(id)
        result.enqueue(object :Callback<List<SmDataItem>?>{
            override fun onResponse(
                call: Call<List<SmDataItem>?>,
                response: Response<List<SmDataItem>?>
            ) {
                var responseBody=response.body()
                for (data in responseBody!!){
                    tv_EDfullname.setText("${data.fname} ${data.lname}")
                    tv_EDemail.setText("Email : ${data.email}")
                    tv_EDMo_number.setText("Phone Number : ${data.mo_number}")
                    tv_EDFam_Members.setText("Family Members : ${data.f_member}")
                    tv_EDOccupation.setText("Occupation : ${data.occupation}")
                    tv_EDFlate_no.setText("House No. : ${data.flate_number}")

                    pragDialog.dismiss()
                }
            }

            override fun onFailure(call: Call<List<SmDataItem>?>, t: Throwable) {
                Toast.makeText(this@InDetailsSmViewByCmActivity, "Full Data Fatch Failed", Toast.LENGTH_SHORT).show()
            }

        })

        //UPDATE
        var btn_update_sm_details=findViewById<AppCompatButton>(R.id.btn_update_sm_details)
        btn_update_sm_details.setOnClickListener {
            var i=Intent(this,UpdateSmByCmActivity::class.java)
            i.putExtra("keyid",id)
            startActivity(i)
        }



        //DELETE
        var btn_delete1=findViewById<AppCompatButton>(R.id.btn_delete1)
        btn_delete1.setOnClickListener {
            pragDialog.setTitle("Delete...")
            pragDialog.setMessage("Please Wait Deleting Your Data...")
            pragDialog.show()

            var result2=retrofit.deleteSmData(id)
            result2.enqueue(object :Callback<SmDataItem?>{
                override fun onResponse(call: Call<SmDataItem?>, response: Response<SmDataItem?>) {
                    //Toast.makeText(this@InDetailsSmViewByCmActivity, "------deleted-------", Toast.LENGTH_SHORT).show()
                    var i2=Intent(this@InDetailsSmViewByCmActivity, CMHomeScreenActivity::class.java)
                    finish()
                    startActivity(i2)
                }

                override fun onFailure(call: Call<SmDataItem?>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }

    }
}