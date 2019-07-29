package com.designloft.ui.selectBackground.tampletes

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.designloft.R
import com.designloft.models.RoomImage
import kotlinx.android.synthetic.main.view_image_room.view.*

class ImageAdapter (
    private val options: RequestOptions,
    private val clickListener: (Drawable) -> Unit
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val list = ArrayList<RoomImage>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        context = parent.context
        return ImageViewHolder(
            LayoutInflater.from(context).inflate(R.layout.view_image_room, parent, false)
            , options
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int ) {
        holder.updateItem(list[position], Glide.with(context))
        holder.itemView.setOnClickListener {
            it.my_room_item_image.drawable
            clickListener(it.my_room_item_image.drawable) }
    }

    fun setItems(entities: MutableList<RoomImage>) {
        list.clear()
        list.addAll(entities)
        notifyDataSetChanged()
    }

    class ImageViewHolder(itemView: View, val options: RequestOptions) : RecyclerView.ViewHolder(itemView) {
        fun updateItem(model: RoomImage, glide: RequestManager) {
            glide.load(model.link)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(itemView.my_room_item_image)
        }
    }
}