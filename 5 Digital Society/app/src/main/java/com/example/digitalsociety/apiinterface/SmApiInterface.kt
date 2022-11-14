package com.example.digitalsociety.apiinterface

import com.example.digitalsociety.dataitem.NoticeDataItem
import com.example.digitalsociety.dataitem.SmDataItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SmApiInterface
{
    // REGISTRATION
    @FormUrlEncoded
    @POST("insert_member.php")
    fun insertRegistrationData(@Field("fname") fname:String,@Field("lname") lname:String,@Field("email") email:String,@Field("mo_number") mo_number:String,@Field("f_member") f_member:String,@Field("occupation") occupation:String,@Field("flate_number") flate_number:String,@Field("password") password:String):Call<SmDataItem>

    // SHOW ALL MEMBERS
    @GET("get_all_members.php")
    fun get_All_Members():Call<List<SmDataItem>>

    // SHOW SPECIFIC MEMBER DETAILS
    @GET("get_spec_sm_by_id.php")
    fun get_spec_sm(@Query("id") id:Int):Call<List<SmDataItem>>

    // UPDATE SPEC DETAILS
    @FormUrlEncoded
    @POST("update_sm_details.php")
    fun updateSmDetails(@Field("id") id:Int,@Field("fname") fname:String,@Field("lname") lname:String,@Field("email") email:String,@Field("mo_number") mo_number:String,@Field("f_member") f_member:String,@Field("occupation") occupation:String,@Field("flate_number") flate_number:String):Call<SmDataItem>

    //DELETE MEMBER
    @FormUrlEncoded
    @POST("delete_sm_data.php")
    fun deleteSmData(@Field("id") id:Int):Call<SmDataItem>

    //SOCITY MEMBER LOGIN
    @GET("sm_login.php")
    fun loginSMembers(@Query("email") email: String,@Query("password") password: String):Call<List<SmDataItem>>

    // SHOW Notice For MEMBERS
    @GET("get_notice.php")
    fun get_Notice():Call<List<NoticeDataItem>>
}