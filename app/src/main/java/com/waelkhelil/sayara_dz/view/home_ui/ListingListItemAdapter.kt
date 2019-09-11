package com.waelkhelil.sayara_dz.view.home_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.model.Listing

class ListingListItemAdapter( var list: List<Listing>)
    : RecyclerView.Adapter<ListingListItemAdapter.ListingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListingViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
    fun setListingList(pList: List<Listing>){
        this.list = pList
        notifyDataSetChanged()
    }
    inner class ListingViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_image, parent, false)){
        fun bind(item: Any?) {

        }
    }
}