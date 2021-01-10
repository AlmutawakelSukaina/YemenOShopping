package com.yemen.oshopping.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yemen.oshopping.api.OshoppingApi
import com.yemen.oshopping.api.ProductResponse
import com.yemen.oshopping.model.ProductItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FetchData {

    private val oshoppingApi: OshoppingApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        oshoppingApi = retrofit.create(OshoppingApi::class.java)
    }

    fun fetchProduct(): LiveData<List<ProductItem>> {
        val responseLiveData: MutableLiveData<List<ProductItem>> = MutableLiveData()
        val productRequest: Call<ProductResponse> = oshoppingApi.fetchProduct()

        productRequest.enqueue(object : Callback<ProductResponse> {

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e("fetchNews", "Failed to fetch news",t)
            }

            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                Log.d("fetchNews", "Response received correctly")
                val productResponse: ProductResponse? = response.body()
                var productItems: List<ProductItem> = productResponse?.productItem
                    ?: mutableListOf()
               // Log.d("fetchallNews", productItems.toString())
                responseLiveData.value = productItems
            }
        })

        return responseLiveData
    }
}