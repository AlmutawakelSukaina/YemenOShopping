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
import com.yemen.oshopping.retrofit.UpdateData


class OshoppingViewModel : ViewModel() {

    val productItemLiveData: LiveData<List<ProductItem>>
    var productLiveData = MutableLiveData<Int>()
    val mutableSearchTerm = MutableLiveData<String>()

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

    var searchLiveData: LiveData<List<ProductItem>> =
        Transformations.switchMap(mutableSearchTerm) { query ->
            FetchData().searchProduct(query)
        }

    fun search(query: String) {
        mutableSearchTerm.value = query
    }

    fun pushcat(category: Category) = PushData().pushCategory(category)
    fun pushProduct(product: ProductDetails) = PushData().pushProduct(product)

    //update data in database
    fun updateCategory(category: Category) = UpdateData().updateCategory(category)


}