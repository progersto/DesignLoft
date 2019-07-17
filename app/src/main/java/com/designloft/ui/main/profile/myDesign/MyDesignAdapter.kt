package com.designloft.ui.main.profile.myDesign

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
import com.designloft.database.entities.MyDesignItem
import kotlinx.android.synthetic.main.item_my_design.view.*
import java.util.ArrayList

class MyDesignAdapter (
    private val options: RequestOptions,
    private val myDesignListener: MyDesignListener
) : RecyclerView.Adapter<MyDesignAdapter.MyDesignViewHolder>() {

    private val list = ArrayList<MyDesignItem>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDesignViewHolder {
        context = parent.context
        return MyDesignViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_my_design, parent, false)
            , options
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyDesignViewHolder, position: Int ) {
        holder.updateItem(list[position], context, Glide.with(context), myDesignListener)

        holder.itemView.setOnClickListener {
            myDesignListener.onItemClick(list[position])
        }

    }

    fun setItems(items: MutableList<MyDesignItem>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }


    class MyDesignViewHolder(itemView: View, val options: RequestOptions) : RecyclerView.ViewHolder(itemView) {


        fun updateItem(
            model: MyDesignItem,
            context: Context,
            glide: RequestManager,
            myDesignListener: MyDesignListener
        ) {
            itemView.my_design_item_delete.setOnClickListener {
                myDesignListener.onItemDelete(model)
            }
            itemView.my_design_item_name.text = model.name
            glide.load(model.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(itemView.my_design_item_image)
        }
    }
}