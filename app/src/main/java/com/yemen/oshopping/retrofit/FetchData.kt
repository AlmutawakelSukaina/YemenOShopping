package com.yemen.oshopping.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.yemen.oshopping.api.OshoppingApi
import com.yemen.oshopping.api.ProductResponse
import com.yemen.oshopping.model.ProductItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TAG = "fetchProduct"

class FetchData {

    private val oshoppingApi: OshoppingApi

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        oshoppingApi = retrofit.create(OshoppingApi::class.java)
    }

    fun fetchProduct(): LiveData<List<ProductItem>> {
        return fetchProduct1(oshoppingApi.fetchProduct())
    }

    fun fetchProductByCategory(category_id: Int): LiveData<List<ProductItem>> {
        return fetchProduct1(oshoppingApi.fetchProductByCategory(category_id))
    }

    fun fetchProduct1(productRequest: Call<ProductResponse>): LiveData<List<ProductItem>> {
        val responseLiveData: MutableLiveData<List<ProductItem>> = MutableLiveData()

        productRequest.enqueue(object : Callback<ProductResponse> {

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.d(TAG, "Failed to fetch Product", t)
            }

            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                Log.d(TAG, "Response received successfully")
                val productResponse: ProductResponse? = response.body()
                val productItems: List<ProductItem> = productResponse?.productItem
                    ?: mutableListOf()
                responseLiveData.value = productItems
            }
        })

        return responseLiveData
    }
}