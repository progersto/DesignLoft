package com.designloft.ui.main.product

import android.graphics.Paint
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
import com.designloft.ui.main.MainViewModel
import com.designloft.utils.roundOffDecimal
import com.designloft.utils.roundOffDecimalOne
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.android.synthetic.main.item_product_size.view.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductFragment : BaseFragment() {
    private lateinit var productsAdapter: ProductPhotoAdapter
    private val viewModel by sharedViewModel<MainViewModel>()

    private val productId by lazy {
        arguments?.getInt(PRODUCT_ID)
    }

    private val productName by lazy {
        arguments?.getString(PRODUCT_NAME)
    }

    companion object {
        const val TAG = "ProductsFragment"

        private const val PRODUCT_ID = "product_id"
        private const val PRODUCT_NAME = "product_name"
        fun newInstance(productId: Int, productName: String) = ProductFragment().apply {
            arguments = Bundle().apply {
                putInt(PRODUCT_ID, productId)
                putString(PRODUCT_NAME, productName)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productId?.also {
            viewModel.getProductByyId(it)
        }

        text_toolbar.text = productName
        activity!!.bottom_navigation.visibility = View.GONE
        back_btn.setOnClickListener {
            activity?.onBackPressed()
        }

        val options = RequestOptions()
            .override(300, 300)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .error(R.drawable.no_image)
        productsAdapter = ProductPhotoAdapter(options) { photo ->
            Log.d("ddddd", " dddd")
        }
        fragment_product_photo_adapter.adapter = productsAdapter

        viewModel.product.observe(myLifecycleOwner, Observer { product ->
            product?.also {
                fragment_product_sale.visibility = if (product.sale) View.VISIBLE else View.GONE
                fragment_product_favorite.isChecked = product.favorite
                fragment_product_favorite.setOnCheckedChangeListener { _, isChecked ->
                    viewModel.updateProductFavorite(it.copy(favorite = isChecked))
                }
                fragment_product_old_price.visibility = if (product.oldPrice > 0) View.VISIBLE else View.GONE
                val price = "${product.price.roundOffDecimal()} $"
                fragment_product_price.text = price
                val oldPrice = "${product.oldPrice.roundOffDecimal()} $"
                fragment_product_old_price.text = oldPrice
                fragment_product_old_price.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                fragment_product_buy_btn.setOnClickListener { }
                fragment_product_measure.setOnClickListener { }
                fragment_product_height.fragment_product_size_data.text = product.height.roundOffDecimalOne()
                fragment_product_width.fragment_product_size_data.text = product.width.roundOffDecimalOne()
                fragment_product_length.fragment_product_size_data.text = product.length.roundOffDecimalOne()
                fragment_product_height.fragment_product_size_title.text = resources.getString(R.string.product_size_title_height)
                fragment_product_width.fragment_product_size_title.text = resources.getString(R.string.product_size_title_width)
                fragment_product_length.fragment_product_size_title.text = resources.getString(R.string.product_size_title_length)
                fragment_product_description.text = product.description

                productsAdapter.setItems(product.imageList)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity!!.bottom_navigation.visibility = View.VISIBLE
    }
}
