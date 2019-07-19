package com.designloft.ui.main.categories.products

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
import com.designloft.models.Product
import kotlinx.android.synthetic.main.item_product.view.*
import java.util.ArrayList

class ProductsAdapter(
    private val options: RequestOptions,
    private val productsListener: ProductsListener
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private val list = ArrayList<Product>()
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
        holder.updateItem(list[position], context, Glide.with(context), productsListener)

        holder.itemView.setOnClickListener {
            productsListener.onItemClick(list[position])
        }
    }

    fun setItems(entities: MutableList<Product>) {
        list.clear()
        list.addAll(entities)
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: View, val options: RequestOptions) : RecyclerView.ViewHolder(itemView) {

        fun updateItem(
            model: Product,
            context: Context,
            glide: RequestManager,
            productsListener: ProductsListener
        ) {
            itemView.product_item_name.text = model.name
            val price = "${model.price} $"
            itemView.product_item_price.text = price
            itemView.product_item_favorite.background = if (model.favorite) context.resources.getDrawable(R.drawable.star2) else context.resources.getDrawable(R.drawable.star_empty)
            itemView.product_sale.visibility = if (model.sale) View.VISIBLE else View.GONE

            glide.load(model.imageList[0].imageLink)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(itemView.product_item_image)
        }
    }
}