package com.waelkhelil.sayara_dz.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.sayara_dz.database.Brand

class BrandsListAdapter(private val list: List<Brand>)
    : RecyclerView.Adapter<BrandViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BrandViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brand: Brand = list[position]
        holder.bind(brand)
    }

    override fun getItemCount(): Int = list.size

}