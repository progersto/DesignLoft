package com.designloft.ui.main.profile.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.models.Product
import com.designloft.ui.main.MainViewModel
import com.designloft.ui.main.categories.products.ProductsAdapter
import com.designloft.ui.main.categories.products.ProductsListener
import com.designloft.ui.main.categories.products.product.ProductFragment
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteFragment : BaseFragment() {

    private lateinit var productsAdapter: ProductsAdapter
    private var productList = ArrayList<Product>()
    private lateinit var productsListener: ProductsListener

    private val viewModel by sharedViewModel<MainViewModel>()

    companion object {
        const val TAG = "ProductsFragment"
        private const val CATEGORY_NAME = "category_name"

        fun newInstance() = FavoriteFragment().apply {
            arguments = Bundle().apply {
//                putString(CATEGORY_NAME, categoryName)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFavorites()

        val options = RequestOptions()
            .override(200, 200)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .error(R.drawable.no_image)

        productsListener = object : ProductsListener {

            override fun onItemFavorite(product: Product) {
                viewModel.updateProductFavorite(product)
            }

            override fun onItemClick(product: Product) {
                showFragment(ProductFragment.newInstance(product.id, product.name), R.id.container_favorite)
            }
        }
        productsAdapter = ProductsAdapter(options, productsListener)
        favorite_adapter.adapter = productsAdapter

        viewModel.favorites.observe(myLifecycleOwner, Observer { list ->
            list?.also {
                productList.clear()
                productList.addAll(list)
                productsAdapter.setItems(list)
            }
        })
    }
}
