package com.waelkhelil.sayara_dz.view.models_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.model.Model

class ModelItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.model_list_item, parent, false)){
    //TODO : convert this class to a generic class
    private var mModel: Model? = null
    private var mTitleView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.text_card_header)
        this.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.modelFragment)
        )
    }

    fun bind(model: Model) {
        mTitleView?.text = model.name
        mModel = model
        Glide
            .with(itemView)
            .load(model.url)
            .placeholder(R.drawable.icon_mono)
            .into(itemView.findViewById(R.id.image_list_item_logo))
    }

}