package com.yemen.oshopping.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yemen.oshopping.R
import com.yemen.oshopping.model.ProductItem
import com.yemen.oshopping.model.User
import com.yemen.oshopping.viewmodel.OshoppingViewModel
import java.io.File

class ProductDetailsFragment :Fragment(){
    lateinit var productImage:ImageView
    lateinit var productName: TextView
    lateinit var yrialProductPrice: TextView
    lateinit var dollarProductPrice: TextView
    lateinit var productQuantity: TextView
    lateinit var productDiscount: TextView
    lateinit var productDetails: TextView
    lateinit var newsItem:ProductItem
    var product_id=1
    lateinit var oshoppingViewModel: OshoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        oshoppingViewModel = ViewModelProviders.of(this).get(OshoppingViewModel::class.java)
        oshoppingViewModel.getProductById(product_id)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)
        productImage = view.findViewById(R.id.product_img_view)
        productName = view.findViewById(R.id.product_name_text_view)
        yrialProductPrice = view.findViewById(R.id.yrial_price_text_view)
        dollarProductPrice = view.findViewById(R.id.dollar_price_text_view)
        productQuantity = view.findViewById(R.id.prodcut_quantity_text_view)
        productDiscount = view.findViewById(R.id.prodcut_discount_text_view)
        productDetails = view.findViewById(R.id.product_details_text_view)

        return view
        }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        oshoppingViewModel.productItemLiveDataByID.observe(
            viewLifecycleOwner,
            Observer { productDetails ->
                productDetails?.let {
                    Log.d("FromObserver", "${it.toString()}")
                    this.newsItem = productDetails
                    updateUI()
                }
            })
    }

//    fun browsePic(){
//        var imgFile = File();
//        if(imgFile.exists()){
//             var myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//            productImage.setImageBitmap(myBitmap);
//        }
//    }
    fun updateUI(){
        productName.text= newsItem.product_name
        yrialProductPrice.text= newsItem.yrial_price.toString()
        dollarProductPrice.text= newsItem.dollar_price.toString()
        productQuantity.text= newsItem.product_quantity.toString()
        productDiscount.text= newsItem.product_discount.toString()
        productDetails.text= newsItem.product_details
    }

    companion object {
        fun newInstance() =
            ProductDetailsFragment()
    }


    }
