package com.yemen.oshopping.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yemen.oshopping.R
import com.yemen.oshopping.model.ProductItem
import com.yemen.oshopping.viewmodel.OshoppingViewModel
import java.text.SimpleDateFormat


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ShowProductFragment : Fragment() {


    interface Callbacks {
        fun onProductSelected(product_id: Int)
    }

    private var callbacks: Callbacks? = null

    private lateinit var oshoppingViewModel: OshoppingViewModel
    private lateinit var showProductRecyclerView: RecyclerView
    private var showProductByCategory: String? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            showProductByCategory = it.getString(ARG_PARAM1)

        }

        oshoppingViewModel =
            ViewModelProviders.of(this).get(OshoppingViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_show_product, container, false)
        showProductRecyclerView = view.findViewById(R.id.show_product_recycler_view)
        showProductRecyclerView.layoutManager = GridLayoutManager(context, 1)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

                oshoppingViewModel.productItemLiveData.observe(
                    viewLifecycleOwner, androidx.lifecycle.Observer
                    { productItems ->
                        Log.d("productItemLiveData", "product Item Live Data")
                        updateui(productItems)
                    })






    }

    private fun updateui(productItems: List<ProductItem>) {
        showProductRecyclerView.adapter = ShowProductAdapter(productItems)
    }

    private inner class ShowProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener  {
        init {
            itemView.setOnClickListener(this)

        }
        private lateinit var productItemss: ProductItem
        @SuppressLint("SimpleDateFormat")
        var dateFormatter: SimpleDateFormat = SimpleDateFormat("EE, MM d, yyyy")

        @SuppressLint("SimpleDateFormat")
        var timeFormatter: SimpleDateFormat = SimpleDateFormat("hh:mm a")

        private val productName = itemView.findViewById(R.id.product_nameTv) as TextView
        private val productDate = itemView.findViewById(R.id.product_detailsTv) as TextView
        private val productImage = itemView.findViewById(R.id.product_img) as ImageView


        fun bind(productItems: ProductItem) {
            productItemss=productItems
            productName.text = productItems.product_name
            productDate.text =productItems.product_date
            productImage.apply {

            }

        }


        override fun onClick(v: View?) {
            Toast.makeText(requireContext(),"The id: ${productItemss.product_id} and title ${productItemss.product_name} is clicked",
                Toast.LENGTH_LONG).show()
            callbacks?.onProductSelected(productItemss.product_id)
        }


    }

    private inner class ShowProductAdapter(private val productItems: List<ProductItem>) :
        RecyclerView.Adapter<ShowProductHolder>() {


        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ShowProductHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.show_product_list_item, parent, false)
            return ShowProductHolder(view)
        }

        override fun getItemCount(): Int = productItems.size

        override fun onBindViewHolder(holder: ShowProductHolder, position: Int) {
            val productItems = productItems[position]
            holder.bind(productItems)

        }
    }
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }


    companion object {
        fun newInstance(productCategory: String) = ShowProductFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, productCategory)

            }
        }
    }
}


/*
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_show_product, container, false)
        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShowProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


 */