package com.yemen.oshopping.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yemen.oshopping.model.Category
import com.yemen.oshopping.model.ProductDetails
import com.yemen.oshopping.model.ProductItem
import com.yemen.oshopping.retrofit.FetchData
import com.yemen.oshopping.retrofit.PushData


class OshoppingViewModel : ViewModel() {

    val productItemLiveData: LiveData<List<ProductItem>>
    var productLiveData = MutableLiveData<Int>()

    init {
        productItemLiveData = FetchData().fetchProduct()
    }

    var productItemLiveDataByCategory: LiveData<List<ProductItem>> =
        Transformations.switchMap(productLiveData) { category_id ->
            FetchData().fetchProductByCategory(category_id)
        }

    fun loadProductByCategory(category_id: Int) {
        productLiveData.value = category_id
    }

    fun pushcat(category: Category) = PushData().pushCategory(category)
    fun pushProduct(product: ProductDetails) = PushData().pushProduct(product)


}