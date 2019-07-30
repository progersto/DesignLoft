package com.designloft.ui.dressingRoom

import android.app.Dialog
import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Scroller
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.designloft.R
import com.designloft.models.Product
import com.designloft.ui.main.MainViewModel
import com.designloft.ui.main.product.ProductPhotoAdapter
import com.designloft.utils.roundOffDecimal
import com.designloft.utils.roundOffDecimalOne
import kotlinx.android.synthetic.main.fragment_product.view.*
import kotlinx.android.synthetic.main.item_product_size.view.*
import kotlinx.android.synthetic.main.view_toolbar.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProductDetailsFragment : DialogFragment() {

    companion object {
        const val TAG = "ProductDetailsFragment"
        const val PRODUCT = "product"

        fun newInstance(product: Product) = ProductDetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(PRODUCT, product)
            }
        }
    }

    private val viewModel by sharedViewModel<MainViewModel>()
    private val product by lazy {
        arguments!!.getSerializable(PRODUCT) as Product
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = View.inflate(context, R.layout.fragment_product_for_dressing, null)

        val description = dialogView.findViewById(R.id.fragment_product_description) as TextView
        description.maxLines = 10
        description.setScroller(Scroller(context))
        description.isVerticalScrollBarEnabled = true
        description.movementMethod = ScrollingMovementMethod()

        val dialog = AlertDialog.Builder(activity as Context)
            .setView(dialogView)
            .show()

        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        dialogView.text_toolbar.text = product.name
        dialogView.back_btn.setOnClickListener { dialog.dismiss() }

        val options = RequestOptions()
            .override(300, 300)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .error(R.drawable.no_image)
        val productsAdapter = ProductPhotoAdapter(options) { photo ->
            Log.d("ddddd", " dddd")
        }
        dialogView.fragment_product_photo_adapter.adapter = productsAdapter
        dialogView.fragment_product_sale.visibility = if (product.sale) View.VISIBLE else View.GONE
        dialogView.fragment_product_favorite.isChecked = product.favorite
        dialogView.fragment_product_favorite.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateProductFavorite(product.copy(favorite = isChecked))
        }
        dialogView.fragment_product_old_price.visibility = if (product.oldPrice > 0) View.VISIBLE else View.GONE
        val price = "${product.price.roundOffDecimal()} $"
        dialogView.fragment_product_price.text = price
        val oldPrice = "${product.oldPrice.roundOffDecimal()} $"
        dialogView.fragment_product_old_price.text = oldPrice
        dialogView.fragment_product_old_price.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        dialogView.fragment_product_buy_btn.setOnClickListener { }
        dialogView.fragment_product_height.fragment_product_size_data.text = product.height.roundOffDecimalOne()
        dialogView.fragment_product_width.fragment_product_size_data.text = product.width.roundOffDecimalOne()
        dialogView.fragment_product_length.fragment_product_size_data.text = product.length.roundOffDecimalOne()
        dialogView.fragment_product_height.fragment_product_size_title.text =
            resources.getString(R.string.product_size_title_height)
        dialogView.fragment_product_width.fragment_product_size_title.text =
            resources.getString(R.string.product_size_title_width)
        dialogView.fragment_product_length.fragment_product_size_title.text =
            resources.getString(R.string.product_size_title_length)
        dialogView.fragment_product_description.text = product.description
        productsAdapter.setItems(product.imageList)
        return dialog
    }
}
