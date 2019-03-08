package com.waelkhelil.sayara_dz.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.sayara_dz.database.Brand
import com.waelkhelil.sayara_dz.R

class CardViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.horizontal_list_item, parent, false)) {

    private var mTitleView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.text_card_header)
    }

    fun bind(brand: Brand) {
        mTitleView?.text = brand.name
    }

}