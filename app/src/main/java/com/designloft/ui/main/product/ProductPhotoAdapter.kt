package com.designloft.ui.main.product

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
import com.designloft.database.entities.PhotoProductEntity
import kotlinx.android.synthetic.main.item_product_photo.view.*

class ProductPhotoAdapter (private val options: RequestOptions, private val clickListener: (PhotoProductEntity) -> Unit)
    : RecyclerView.Adapter<ProductPhotoAdapter.ProductPhotoViewHolder>() {

    private val list = mutableListOf<PhotoProductEntity>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductPhotoViewHolder {
        context = parent.context
        return ProductPhotoViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_product_photo, parent, false)
            , options
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ProductPhotoViewHolder, position: Int) {
        holder.updateItem(list[position], context, Glide.with(context))

        holder.itemView.setOnClickListener {
            clickListener(list[position])
        }
    }

    fun setItems(entities: MutableList<PhotoProductEntity>) {
        list.clear()
        list.addAll(entities)
        notifyDataSetChanged()
    }


    class ProductPhotoViewHolder(itemView: View, val options: RequestOptions) : RecyclerView.ViewHolder(itemView) {

        fun updateItem(model: PhotoProductEntity, context: Context, glide: RequestManager) {

            glide.load(model.imageLink)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(itemView.item_product_photo_image)
        }
    }
}