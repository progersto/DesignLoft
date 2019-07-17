package com.designloft.ui.main.categories.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.designloft.R
import com.designloft.database.entities.ProductItem
import kotlinx.android.synthetic.main.item_product.view.*
import java.util.ArrayList

class ProductAdapter (private val options: RequestOptions, private val clickListener: (ProductItem) -> Unit)

    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val list = ArrayList<ProductItem>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        context = parent.context
        return ProductViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
            , options
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.updateItem(list[position], context, Glide.with(context))

        holder.itemView.setOnClickListener {
            clickListener(list[position])
        }
    }

    fun setItems(items: MutableList<ProductItem>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }


    class ProductViewHolder(itemView: View, val options: RequestOptions) : RecyclerView.ViewHolder(itemView) {

        fun updateItem(model: ProductItem, context: Context, glide: RequestManager) {
            itemView.product_item_name.text = model.name
            val price =  "${model.price} $"
            itemView.product_item_price.text = price
            itemView.product_item_favorite.isChecked = model.favorite
            itemView.product_sale.visibility = if (model.sale) View.VISIBLE else View.GONE

            glide.load(model.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(itemView.product_item_image)
        }
    }
}