package com.designloft.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.database.entities.ProductItem
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductsFragment : BaseFragment() {

    private lateinit var productAdapter: ProductAdapter
    private var productList = ArrayList<ProductItem>()

    private val viewModel by sharedViewModel<MainViewModel>()

    private val categoryName by lazy {
        arguments?.getString(CATEGORY_NAME)
    }

    companion object {
        const val TAG = "ProductsFragment"
        private const val CATEGORY_NAME = "category_name"

        fun newInstance(categoryName: String) = ProductsFragment().apply {
            arguments = Bundle().apply {
                putString(CATEGORY_NAME, categoryName)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.products.value?.apply {
            viewModel.getAllProducts()
        }

        text_toolbar.text = categoryName

        val options = RequestOptions()
            .override(200, 200)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .error(R.drawable.no_image)

        productAdapter = ProductAdapter(options) { product ->
            Log.d("ddddd", " dddd")
        }
        products_adapter.adapter = productAdapter

        viewModel.products.observe(myLifecycleOwner, Observer { list ->
            list?.also {
                productList.clear()
                productList.addAll(it)
                productAdapter.setItems(it)
            }
        })
    }
//        auth_login_btn.setOnClickListener {
//            viewModel.showLogin.call()
//        }
}
