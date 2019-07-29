package com.designloft.ui.dressingRoom

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
import com.designloft.ui.main.categories.products.ProductsListener
import kotlinx.android.synthetic.main.item_catalog_product.view.*
import java.util.*

class CatalogProductAdapter(
    private val options: RequestOptions,
    private val productsListener: ProductsListener
) : RecyclerView.Adapter<CatalogProductAdapter.ImageViewHolder>() {

    private val list = ArrayList<Product>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        context = parent.context
        return ImageViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_catalog_product, parent, false),
            context,
            options
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.updateItem(list[position], Glide.with(context), productsListener)
        productsListener.onItemClick(list[position])
    }

    fun setItems(entities: MutableList<Product>) {
        list.clear()
        list.addAll(entities)
        notifyDataSetChanged()
    }

    class ImageViewHolder(itemView: View, val context: Context, val options: RequestOptions) :
        RecyclerView.ViewHolder(itemView) {

        fun updateItem(model: Product, glide: RequestManager, productsListener: ProductsListener) {
            itemView.catalog_product_item_favorite.background =
                if (model.favorite) context.resources.getDrawable(R.drawable.star2) else context.resources.getDrawable(R.drawable.star_empty)

            try {
                glide.load(model.imageList[0].imageLink)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(options)
                    .into(itemView.catalog_product_item_image)
            } catch (e: IndexOutOfBoundsException) {
                itemView.catalog_product_item_image.setImageResource(R.drawable.no_image)
            }

            itemView.catalog_product_detail.setOnClickListener {
                productsListener.onItemFavorite(model)
            }
        }
    }
}