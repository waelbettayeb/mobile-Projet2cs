package com.waelkhelil.sayara_dz.view.home_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.waelkhelil.sayara_dz.database.model.Brand

class CardsListItemAdapter(private val list: List<Brand>) : PagedListAdapter<Brand,CardViewHolder>(Brand_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CardViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val brand: Brand = list[position]
        holder.bind(brand)
    }

    override fun getItemCount(): Int = list.size

    companion object {
        private val Brand_COMPARATOR = object : DiffUtil.ItemCallback<Brand>() {
            override fun areItemsTheSame(oldItem: Brand, newItem: Brand): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Brand, newItem: Brand): Boolean =
                oldItem == newItem
        }
    }

}
