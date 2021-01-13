package com.yemen.oshopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yemen.oshopping.ui.AddCategoryFragment
import com.yemen.oshopping.ui.AddUserFragment
import com.yemen.oshopping.ui.ProductDetailsFragment

class MainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        supportActionBar?.hide()
        val fragment= ProductDetailsFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment).addToBackStack(null)
            .commit()
    }
}