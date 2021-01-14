package com.yemen.oshopping.retrofit

import android.util.Log
import com.yemen.oshopping.model.Category
import com.yemen.oshopping.model.DefaultResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteData {
    fun deleteCategory(category: Category){

        val deleteCategoryRequest: Call<DefaultResponse> = RetrofitClient().oshoppingApi
            .deleteCategory(category.cat_id,category.cat_name)

        deleteCategoryRequest.enqueue(object : Callback<DefaultResponse> {

            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                Log.e("deleteCategory", "Failed to delete Category", t)

            }

            override fun onResponse(
                call: Call<DefaultResponse>,
                response: Response<DefaultResponse>
            ) {
                Log.d("deleteCategory", "Category deleted successfully")

            }
        })

    }
}