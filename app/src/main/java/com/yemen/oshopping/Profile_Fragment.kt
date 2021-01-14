package com.yemen.oshopping

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment



class Profile_Fragment:Fragment(),View.OnClickListener {

    lateinit var goBack: ImageView
    lateinit var myAccount: TextView
    lateinit var myProduct: TextView
    lateinit var myBalance: TextView
    lateinit var aboutUs: TextView
    lateinit var deliveryAddresses: TextView
    lateinit var contactUs: TextView
    lateinit var logOut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_profile, container, false)
        goBack=view.findViewById(R.id.go_back)
        myAccount=view.findViewById(R.id.my_account)
        myProduct=view.findViewById(R.id.my_products)
        myBalance=view.findViewById(R.id.my_Balance)
        aboutUs=view.findViewById(R.id.about_us)
        deliveryAddresses=view.findViewById(R.id.delivery_Addresses)
        contactUs=view.findViewById(R.id.contact_us)
        logOut=view.findViewById(R.id.log_out)
        myProduct.setOnClickListener(this)

        return view
    }

    companion object {
        fun newInstance(): Profile_Fragment {
            return Profile_Fragment()
        }
    }

    override fun onClick(v: View?) {
        val intent = Intent(activity, addProduct::class.java)

        startActivity(intent)
    }
}

