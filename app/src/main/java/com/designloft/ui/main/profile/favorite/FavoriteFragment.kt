package com.designloft.ui.main.profile.favorite

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
import com.designloft.ui.main.categories.product.ProductAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteFragment : BaseFragment() {

    private lateinit var productAdapter: ProductAdapter
    private var productList = ArrayList<ProductItem>()

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

        val options = RequestOptions()
            .override(200, 200)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .error(R.drawable.no_image)

        productAdapter = ProductAdapter(options) { product ->
            Log.d("ddddd", " dddd")
        }
        favorite_adapter.adapter = productAdapter

        viewModel.products.observe(myLifecycleOwner, Observer { list ->
            val filterList = list.filter { it.favorite == true } as MutableList<ProductItem>
            list?.also {
                productList.clear()
                productList.addAll(filterList)
                productAdapter.setItems(filterList)
            }
        })
    }
}
