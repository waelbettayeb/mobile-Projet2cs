package com.waelkhelil.sayara_dz.view.brands_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.waelkhelil.sayara_dz.model.Brand
import com.waelkhelil.sayara_dz.R

class BrandViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_brand, parent, false)) {

    private var mTitleView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.text_list_item_name)
        this.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.modelsFragment)
        )
    }

    fun bind(brand: Brand) {
        mTitleView?.text = brand.name
        Glide
            .with(itemView)
            .load(brand.url)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.user_icon)
            .into(itemView.findViewById(R.id.image_list_item_logo))
    }

}