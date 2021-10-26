package com.excel.myapplication.utils

import com.excel.myapplication.model.DataRes
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

import okhttp3.OkHttpClient
import okhttp3.Request


interface RetrofitService {

    @GET("alert/moduleAlerts?marine_id=0&alert_module_type=VesselsOperations&page=1")
    fun getAllMovies() : Call<DataRes>

    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {

                val okHttpClient = OkHttpClient().newBuilder().addInterceptor { chain ->
                    val originalRequest: Request = chain.request()
                    val builder: Request.Builder = originalRequest.newBuilder().header(
                        "Authorization",
                        Credentials.basic("PortOfOakland", "OaklandPortal@2022!*")
                    )
                    val newRequest: Request = builder.build()
                    chain.proceed(newRequest)
                }.build()


            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://portoakland-stageapi.mobileprogramming.net/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)

            }
            return retrofitService!!
        }


    }




}