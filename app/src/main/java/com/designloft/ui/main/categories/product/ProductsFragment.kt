package com.designloft.ui.main.categories.product

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
import com.designloft.ui.main.MainViewModel
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

    private val categoryId by lazy {
        arguments?.getInt(CATEGORY_ID)
    }

    companion object {
        const val TAG = "ProductsFragment"
        private const val CATEGORY_NAME = "category_name"
        private const val CATEGORY_ID = "category_Id"

        fun newInstance(categoryName: String, categoryId: Int) = ProductsFragment().apply {
            arguments = Bundle().apply {
                putString(CATEGORY_NAME, categoryName)
                putInt(CATEGORY_ID, categoryId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            val filterList = list.filter { it.categoryId == categoryId } as MutableList<ProductItem>
            list?.also {
                productList.clear()
                productList.addAll(filterList)
                productAdapter.setItems(filterList)
            }
        })
    }


}
