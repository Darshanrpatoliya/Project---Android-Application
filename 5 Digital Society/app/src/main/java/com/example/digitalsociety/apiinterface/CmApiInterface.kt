package com.example.digitalsociety.apiinterface

import com.example.digitalsociety.dataitem.CmDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CmApiInterface
{
    @GET("cm_login.php")
    fun loginCM(@Query("email") email:String,@Query("password") password:String):Call<List<CmDataItem>>
}