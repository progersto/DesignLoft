package com.designloft.ui.ar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.designloft.R
import com.designloft.models.ModelItem
import kotlinx.android.synthetic.main.item.view.*
import kotlin.collections.ArrayList


class PositionsAdapter(
    private val mDataset: ArrayList<ModelItem>,
    private val clickListener: (ModelItem) -> Unit
) : RecyclerView.Adapter<PositionsAdapter.EventsParticipatesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsParticipatesViewHolder {
        return EventsParticipatesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount() = mDataset.size

    override fun onBindViewHolder(holder: EventsParticipatesViewHolder, position: Int) {
        holder.bind(mDataset[position], clickListener)
    }

    inner class EventsParticipatesViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        fun bind(item: ModelItem, clickListener: (ModelItem) -> Unit) {
            itemView.name_item.text = item.name
            itemView.setOnClickListener {
                clickListener(item)
            }
        }
    }
}