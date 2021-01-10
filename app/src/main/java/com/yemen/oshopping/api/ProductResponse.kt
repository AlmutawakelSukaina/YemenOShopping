package com.yemen.oshopping.api


import com.google.gson.annotations.SerializedName
import com.yemen.oshopping.model.ProductItem

data class ProductResponse (
    @SerializedName("listOfProduct")
    var productItem: List<ProductItem>

)