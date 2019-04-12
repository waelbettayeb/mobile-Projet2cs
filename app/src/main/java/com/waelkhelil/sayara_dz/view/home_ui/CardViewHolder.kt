package com.waelkhelil.sayara_dz.view.home_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.sayara_dz.model.Brand
import com.waelkhelil.sayara_dz.R

class CardViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.horizontal_list_item, parent, false)){
    //TODO : convert this class to a generic class
    private var mBrand: Brand? = null
    private var mTitleView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.text_card_header)
        val bundle = bundleOf("brand_id" to mBrand?.id)
    
        this.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.modelsFragment, bundle)
        )
    }

    fun bind(brand: Brand) {
        mTitleView?.text = brand.name
        mBrand = brand
    }


}