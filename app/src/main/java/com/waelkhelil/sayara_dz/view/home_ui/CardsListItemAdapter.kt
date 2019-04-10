package com.waelkhelil.sayara_dz.view.home_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.sayara_dz.model.Brand

class CardsListItemAdapter(private val list: List<Brand>)
    : RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CardViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val brand: Brand = list[position]
        holder.bind(brand)
    }

    override fun getItemCount(): Int = list.size

}