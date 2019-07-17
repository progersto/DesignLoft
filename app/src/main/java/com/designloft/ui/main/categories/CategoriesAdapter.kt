package com.designloft.ui.main.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.designloft.R
import com.designloft.database.entities.CategoryItem
import kotlinx.android.synthetic.main.item_category.view.*
import java.util.*

class CategoriesAdapter (private val options: RequestOptions, private val clickListener: (CategoryItem) -> Unit)

    : RecyclerView.Adapter<CategoriesAdapter.CatalogViewHolder>() {

    private val list = ArrayList<CategoryItem>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        context = parent.context
        return CatalogViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
            , options
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.updateItem(list[position], context, Glide.with(context))

        holder.itemView.setOnClickListener {
            clickListener(list[position])
        }
    }

    fun setItems(items: MutableList<CategoryItem>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }


    class CatalogViewHolder(itemView: View, val options: RequestOptions) : RecyclerView.ViewHolder(itemView) {
        private lateinit var picture: ImageView
        private lateinit var name: TextView

        fun updateItem(model: CategoryItem, context: Context, glide: RequestManager) {
            itemView.catalog_item_name.text = model.name

            picture = itemView.catalog_item_image
            glide.load(model.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(picture)
        }
    }
}