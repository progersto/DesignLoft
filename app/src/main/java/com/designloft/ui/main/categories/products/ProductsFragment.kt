package com.designloft.ui.main.categories.products

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.models.Product
import com.designloft.ui.main.MainViewModel
import com.designloft.ui.main.categories.products.product.ProductFragment
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductsFragment : BaseFragment() {

    private lateinit var productsAdapter: ProductsAdapter
    private var productList = ArrayList<Product>()
    private lateinit var productsListener: ProductsListener
    private lateinit var imm: InputMethodManager

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

        categoryId?.also { viewModel.getProductsByCategoryId(it) }

        imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        back_btn.setOnClickListener { activity?.onBackPressed() }
        text_toolbar.text = categoryName
        search_btn.setOnClickListener {
            back_btn.visibility = View.GONE
            text_toolbar.visibility = View.GONE
            search_btn.visibility = View.GONE
            filter_btn.visibility = View.GONE
            searchText.visibility = View.VISIBLE
            clearSearch.visibility = View.VISIBLE
            searchText.requestFocus()
            searchText.addTextChangedListener(textWatcher)
        }
        searchText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
            } else {
                imm.hideSoftInputFromWindow(searchText.windowToken, 0)
            }
        }
        clearSearch.setOnClickListener {
            back_btn.visibility = View.VISIBLE
            text_toolbar.visibility = View.VISIBLE
            search_btn.visibility = View.VISIBLE
            filter_btn.visibility = View.VISIBLE
            searchText.visibility = View.GONE
            clearSearch.visibility = View.GONE
            searchText.setText("")

        }

        val options = RequestOptions()
            .override(200, 200)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .error(R.drawable.no_image)
        productsListener = object : ProductsListener {

            override fun onItemFavorite(product: Product) {
                viewModel.updateProduct(product)
            }

            override fun onItemClick(product: Product) {
                showFragment(ProductFragment.newInstance(product.id, product.name), R.id.container_category)
            }
        }
        productsAdapter = ProductsAdapter(options, productsListener)
        products_adapter.adapter = productsAdapter

        viewModel.products.observe(viewLifecycleOwner, Observer { list ->
            list?.also {
                productList.clear()
                productList.addAll(list)
                productsAdapter.setItems(list)
            }
        })
    }

    private val textWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val newList = productList.filter { s in it.name.toLowerCase() } as MutableList
            productsAdapter.setItems(newList)
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {}
    }

}
