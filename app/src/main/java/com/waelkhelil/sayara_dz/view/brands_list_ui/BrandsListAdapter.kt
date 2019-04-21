package com.waelkhelil.sayara_dz.view.brands_list_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView
import com.waelkhelil.sayara_dz.model.Brand

class BrandsListAdapter(private val list: List<Brand>)
    : RecyclerView.Adapter<BrandViewHolder>(), FastScrollRecyclerView.SectionedAdapter{
    var brands:MutableList<Brand>

    init {
        brands = list.sortedBy { brand: Brand -> brand.name }.toMutableList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BrandViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val brand: Brand = brands[position]
        holder.bind(brand)
    }

    override fun getItemCount(): Int = list.size
    override fun getSectionName(position: Int): String {
        return brands[position].name[0].toString()
    }
}