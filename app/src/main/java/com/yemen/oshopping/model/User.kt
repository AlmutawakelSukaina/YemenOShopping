package com.yemen.oshopping.model

data class User(
    var user_id: Int?=null,
    val first_name: String,
    val last_name: String,
    val email: String,
    val latitude: Double,
    val longitude: Double,
    val details: String,
    val is_vendor: Int=0, // ( 0 -> user ) ( 1 -> vendor)
    val block: Int =0, // ( 0 -> active ) ( 1 -> blocked)
    val create_at: String?=null
)