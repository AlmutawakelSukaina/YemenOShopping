package com.yemen.oshopping

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yemen.oshopping.model.Category
import com.yemen.oshopping.viewmodel.OshoppingViewModel

class AdminScreen:Fragment()  {

    private lateinit var fab: FloatingActionButton
    private lateinit var delete_button:ImageView
    lateinit var addCategoryBtn: Button
    lateinit var updateCategoryBtn: Button
    lateinit var addCategoryEditText: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       categoryViewModel = ViewModelProviders.of(this).get(OshoppingViewModel::class.java)
    }
     interface Callbacks {
         fun onCategorySelected(categoryId: Int?)
     }
    private var callbacks: Callbacks? = null

    private lateinit var categoryViewModel: OshoppingViewModel
    private lateinit var categoryRecyclerView: RecyclerView
    private val oshoppingViewModel: OshoppingViewModel by lazy {
       ViewModelProviders.of(this).get(OshoppingViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.admin_screen, container, false)

        categoryRecyclerView = view.findViewById(R.id.recyclerview_admin)
        categoryRecyclerView.layoutManager = LinearLayoutManager(context)
//        addCategoryEditText=view.findViewById(R.id.category) as EditText

        fab = view.findViewById(R.id.fab)

        fab.setOnClickListener {
            val cat= Category(cat_name=addCategoryEditText.text.toString())
            oshoppingViewModel.pushcat(cat)
            callbacks?.onCategorySelected(cat.cat_id)
        }

        delete_button.setOnClickListener{
          //  oshoppingViewModel.deleteCategory()
        }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryViewModel.categoryItemLiveData.observe(
            viewLifecycleOwner,
            Observer { categorys ->
                categoryRecyclerView.adapter = CategoryAdapter(categorys)

            })
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private inner class CategoryHolder(itemTextView: View)
        : RecyclerView.ViewHolder(itemTextView) {
        private lateinit var category: Category
        val addCategoryEditText = itemTextView.findViewById(R.id.category) as EditText

        fun bind(cate: Category){
            addCategoryEditText.setText(cate.cat_name)
        }

       // override
        fun onClick(v: View?) {

            callbacks?.onCategorySelected(category.cat_id)
        }
    }

    private inner class CategoryAdapter(private val categorys: List<Category>)
        : RecyclerView.Adapter<CategoryHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): CategoryHolder {
            val View = LayoutInflater.from(parent.context).inflate(R.layout.admin_list_category,parent,false)
            return CategoryHolder(View)
        }
        override fun getItemCount(): Int = categorys.size

        override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
            val category = categorys[position]
            holder.bind(category)
        }
    }



    companion object {
        fun newInstance(): AdminScreen {
            return AdminScreen()
        }

    }
}

