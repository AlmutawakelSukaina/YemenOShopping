package com.yemen.oshopping.model

import java.util.*

data class ProductDetails(
    var product_id: Int?=null,
    var product_name: String,
    var yrial_price: Double,
    var dollar_price: Double,
    var vendor_id:Int,
    var cat_id:Int,
    var product_details: String,
    var product_img: String,
    var product_date: String?=null,
    var product_quantity: Int,
    var product_discount: Int
)