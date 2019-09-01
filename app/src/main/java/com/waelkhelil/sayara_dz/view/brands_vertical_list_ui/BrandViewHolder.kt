package com.waelkhelil.sayara_dz.view.brands_vertical_list_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.model.Brand

class BrandViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_brand, parent, false)) {

    private var mTitleView: TextView? = null
    private lateinit var bundle : Bundle

    init {
        mTitleView = itemView.findViewById(R.id.text_list_item_name)

    }

    fun bind(brand: Brand) {
        mTitleView?.text = brand.name
        Glide
            .with(itemView)
            .load(brand.url)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.icon_mono)
            .into(itemView.findViewById(R.id.image_brand_logo))
         bundle = bundleOf("brand_name" to brand.name,"brand_logo" to brand.url)

        this.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.modelsFragment, bundle)
        )
    }

}