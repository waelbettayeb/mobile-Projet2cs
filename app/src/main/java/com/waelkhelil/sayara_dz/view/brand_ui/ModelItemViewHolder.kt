package com.waelkhelil.sayara_dz.view.brand_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.model.Model
import com.waelkhelil.sayara_dz.database.model.PaintColor
import com.waelkhelil.sayara_dz.view.model_ui.ColorListAdapter

class ModelItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.model_list_item, parent, false)){
    private var mModel: Model? = null
    private var mTitleView: TextView? = null
    private var mBrandNameTitleView: TextView? = null

    init {
        mTitleView = itemView.findViewById(R.id.text_card_header)
        mBrandNameTitleView = itemView.findViewById(R.id.tv_brand_name_card)
        this.itemView.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.modelFragment)
        )
    }

    fun bind(
        model: Model,
        brand_name: String
    ) {
        mTitleView?.text = model.name
        mModel = model
        mBrandNameTitleView?.text=brand_name



        val list:List<PaintColor> = listOf(
            PaintColor("red", "#2196F3",0),
            PaintColor("red", "#FF6050",100),
            PaintColor("red", "#FF0E83",200),
            PaintColor("red", "#839BFD",200),
            PaintColor("red", "#DDE3FE",400)
        )
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.rv_colors_list)
        recyclerView.adapter = ColorListAdapter(list)
        Glide
            .with(itemView)
            .load(model.url)
            .placeholder(R.drawable.icon_mono)
            .into(itemView.findViewById(R.id.image_list_item_logo))
    }

}