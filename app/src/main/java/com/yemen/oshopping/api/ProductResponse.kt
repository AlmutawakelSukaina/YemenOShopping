package com.yemen.oshopping.api

import com.OShopping.model.ProductItem
import com.google.gson.annotations.SerializedName

data class ProductResponse (
    @SerializedName("listOfProduct")
    var productItem: List<ProductItem>

)