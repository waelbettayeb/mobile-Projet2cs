package com.waelkhelil.sayara_dz.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.sayara_dz.database.Brand
import com.waelkhelil.sayara_dz.R

class BrandViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_brand, parent, false)) {
    private var mTitleView: TextView? = null
    private var mDescriptionView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.text_list_item_name)
        mDescriptionView = itemView.findViewById(R.id.text_list_item_description)
    }

    fun bind(brand: Brand) {
        mTitleView?.text = brand.name
        mDescriptionView?.text = brand.id
    }

}