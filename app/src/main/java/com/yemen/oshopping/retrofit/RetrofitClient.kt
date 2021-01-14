package com.yemen.oshopping.retrofit

import com.google.gson.GsonBuilder
import com.yemen.oshopping.api.OshoppingApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    val oshoppingApi: OshoppingApi

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit: Retrofit = Retrofit.Builder()

            .baseUrl("http://10.5.203.2/")


            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        oshoppingApi = retrofit.create(OshoppingApi::class.java)
    }
}