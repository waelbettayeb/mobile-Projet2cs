package com.waelkhelil.sayara_dz.view.home_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.model.AdResponse

class ListingListItemAdapter(private var list: List<AdResponse>)
    : RecyclerView.Adapter<ListingListItemAdapter.ListingViewHolder>(){

    private var currentPosition:Int=-1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListingViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        currentPosition=position
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


    inner class ListingViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.listing_list_item, parent, false)){

        fun bind(item: AdResponse) {
            itemView.findViewById<TextView>(R.id.tv_title).text = ""
            itemView.findViewById<TextView>(R.id.tv_price).text = item.minPrice.toString()
            itemView.findViewById<TextView>(R.id.tv_date).text = item.date
            itemView.findViewById<TextView>(R.id.rv_description).text = item.description
            Glide
                .with(itemView)
                .load("https://images.app.goo.gl/1aknjVx6mGhR6cFbA")
                .placeholder(R.drawable.icon_mono)
                .into(itemView.findViewById(R.id.iv_listing))

           val bundle = bundleOf("adId" to currentPosition)
            this.itemView.setOnClickListener {
                Navigation.createNavigateOnClickListener(R.id.listingFragment, bundle).onClick(itemView)
            }
        }
    }
}