package com.yemen.oshopping.api


import com.yemen.oshopping.model.DefaultResponse
import retrofit2.Call
import retrofit2.http.*


interface OshoppingApi {
    //post
    @FormUrlEncoded
    @POST("oshopping_api/api/category_api.php")
    fun postCategory(@Field("cat_name") cat_name: String): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("oshopping_api/api/product_api.php")
    fun pushProduct(
        @Field("product_id") product_id: Int?,
        @Field("product_name") product_name: String,
        @Field("yrial_price") yrial_price: Double,
        @Field("dollar_price") dollar_price: Double,
        @Field("vendor_id") vendor_id: Int,
        @Field("cat_id") cat_id: Int,
        @Field("product_details") product_details: String,
        @Field("product_img") product_img: String,
        @Field("product_date") product_date: String?,
        @Field("proudct_quantity") product_quantity: Int,
        @Field("product_discount") product_discount: Int

    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("oshopping_api/api/user_api.php")
    fun pushUser(
        @Field("user_id") user_id: Int?,
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("email") email: String,
        @Field("latitude") latitude: Double,
        @Field("longitude") longitude: Double,
        @Field("details") details: String,
        @Field("is_vendor") is_vendor: Int,
        @Field("block") block: Int,
        @Field("create_at") create_at: String?
    ): Call<DefaultResponse>

    //get
    @GET("oshopping_api/api/product_api.php")
    fun fetchProduct(): Call<ProductResponse>

    @GET("oshopping_api/api/product_api.php")
    fun fetchProductByCategory(@Query("cat_id") category_id: Int): Call<ProductResponse>

    @GET("oshopping_api/api/product_api.php")
    fun fetchProductById(@Query("product_id") product_id: Int): Call<SingleProductResponse>

    @GET("oshopping_api/api/product_api.php")
    fun searchProduct(@Query("query") query: String): Call<ProductResponse>

    @GET("oshopping_api/api/category_api.php")
    fun fetchCategory(): Call<CategoryResponse>

    @GET("oshopping_api/api/user_api.php")
    fun fetchUsers(): Call<UserResponse>

    @GET("oshopping_api/api/user_api.php")
    fun fetchUser(@Query("user_id") user_id: Int): Call<UserResponse>


    //put
    @FormUrlEncoded
    @PUT("oshopping_api/api/category_api.php")
    fun updateCategory(
        @Field("cat_id") cat_id: Int?, @Field("cat_name") cat_name: String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @PUT("oshopping_api/api/user_api.php")
    fun updateUser(
        @Field("user_id") user_id: Int?,
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("email") email: String,
        @Field("latitude") latitude: Double,
        @Field("longitude") longitude: Double,
        @Field("details") details: String,
        @Field("is_vendor") is_vendor: Int,
        @Field("block") block: Int,
        @Field("create_at") create_at: String?
    ): Call<DefaultResponse>


    @FormUrlEncoded
    @PUT("oshopping_api/api/user_api.php")
    fun blockUser(
        @Field("user_id") user_id: Int?, @Field("block") block: Int
    ): Call<DefaultResponse>


    @DELETE("oshopping_api/api/category_api.php")
    fun deleteCategory(@Field("cat_id") cat_id: Int?,@Field("cat_name") cat_name: String
    ): Call<DefaultResponse>

}