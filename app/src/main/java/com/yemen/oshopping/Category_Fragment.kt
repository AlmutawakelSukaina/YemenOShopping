package com.yemen.oshopping

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yemen.oshopping.model.Category
import com.yemen.oshopping.viewmodel.OshoppingViewModel

private const val TAG = "Category"

class Category_Fragment: Fragment()  {
    private lateinit var categoryViewModel: OshoppingViewModel
    private lateinit var categoryRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryViewModel = ViewModelProviders.of(this).get(OshoppingViewModel::class.java)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.category_recycler, container, false)
        categoryRecyclerView = view.findViewById(R.id.category_recycler_view)
        categoryRecyclerView.layoutManager = GridLayoutManager(context, 1)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryViewModel.categoryItemLiveData.observe(
            viewLifecycleOwner,
            Observer { categorys ->
                Log.d("fetchCategory", "Category fetched successfully ${categorys}")
                categoryRecyclerView.adapter = CategoryAdapter(categorys)

            })
    }

    private class CategoryHolder(itemTextView: View)
        : RecyclerView.ViewHolder(itemTextView) {

        val catrgoryTextView = itemTextView.findViewById(R.id.category) as TextView

        fun bind(cate: Category){
            catrgoryTextView.text=cate.cat_name
        }
    }

    private class CategoryAdapter(private val categorys: List<Category>)

        : RecyclerView.Adapter<CategoryHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): CategoryHolder {
            val View = LayoutInflater.from(parent.context).inflate(R.layout.catagory_fragment,parent,false)
            return CategoryHolder(View)
        }
        override fun getItemCount(): Int = categorys.size

        override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
            val category = categorys[position]
            holder.bind(category)
        }
    }

    companion object {
        fun newInstance(): Category_Fragment {
            return Category_Fragment()
        }
    }
}
