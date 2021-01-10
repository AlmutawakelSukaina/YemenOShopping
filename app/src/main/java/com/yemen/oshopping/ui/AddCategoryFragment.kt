package com.yemen.oshopping.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import com.yemen.oshopping.R
import com.yemen.oshopping.model.Category
import com.yemen.oshopping.viewmodel.OshoppingViewModel
import kotlinx.android.synthetic.main.fragment_add_category.*


class AddCategoryFragment : Fragment() {

    lateinit var addCategoryBtn: Button
    lateinit var updateCategoryBtn: Button
    lateinit var addCategoryEditText: EditText
    lateinit var oshoppingViewModel: OshoppingViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        oshoppingViewModel= ViewModelProviders.of(this).get(OshoppingViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_add_category, container, false)
        addCategoryBtn=view.findViewById(R.id.add_cat_btn)
        updateCategoryBtn=view.findViewById(R.id.update_cat_btn)
        addCategoryEditText=view.findViewById(R.id.add_cat_editText)
        addCategoryBtn.setOnClickListener {
            val cat= Category(cat_name=addCategoryEditText.text.toString())
            oshoppingViewModel.pushcat(cat)

        }
        updateCategoryBtn.setOnClickListener {
            val cat= Category(5,"category1 updated")
            oshoppingViewModel.updateCategory(cat)
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            AddCategoryFragment()
    }
}