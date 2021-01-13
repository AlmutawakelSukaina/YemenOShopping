package com.yemen.oshopping.retrofit

import android.util.Log
import com.yemen.oshopping.model.Category
import com.yemen.oshopping.model.DefaultResponse
import com.yemen.oshopping.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateData {

    fun updateCategory(category: Category) {
        val updateCategoryRequest: Call<DefaultResponse> = RetrofitClient().oshoppingApi
            .updateCategory(category.cat_id, category.cat_name)

        updateCategoryRequest.enqueue(object : Callback<DefaultResponse> {

            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                Log.e("updateCategory", "Failed to update Category", t)

            }

            override fun onResponse(
                call: Call<DefaultResponse>,
                response: Response<DefaultResponse>
            ) {
                Log.d("updateCategory", "Category updated successfully")

            }
        })
    }

    fun updateUser(user: User) {
        val updateUserRequest: Call<DefaultResponse> = RetrofitClient().oshoppingApi
            .updateUser(
                user.user_id,
                user.first_name,
                user.last_name,
                user.email,
                user.latitude,
                user.longitude,
                user.details,
                user.is_vendor,
                user.block,
                user.create_at
            )

        updateUserRequest.enqueue(object : Callback<DefaultResponse> {

            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                Log.e("updateCategory", "Failed to update Category", t)

            }

            override fun onResponse(
                call: Call<DefaultResponse>,
                response: Response<DefaultResponse>
            ) {
                Log.d("updateCategory", "Category updated successfully")

            }
        })
    }
}