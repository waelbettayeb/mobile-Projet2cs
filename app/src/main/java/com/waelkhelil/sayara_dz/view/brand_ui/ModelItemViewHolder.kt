package com.waelkhelil.sayara_dz.view.brand_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
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
    private lateinit var bundle : Bundle


    init {
        mTitleView = itemView.findViewById(R.id.text_card_header)
        mBrandNameTitleView = itemView.findViewById(R.id.tv_brand_name_card)

    }

    fun bind(
        model: Model,
        brand_name: String,
        colorList:List<PaintColor>

    ) {
        mTitleView?.text = model.name
        mModel = model
        mBrandNameTitleView?.text=brand_name


        val recyclerView = itemView.findViewById<RecyclerView>(R.id.rv_colors_list)

         recyclerView.adapter = ColorListAdapter(colorToString(colorList))
        Glide
            .with(itemView)
            .load(model.url)
            .placeholder(R.drawable.icon_mono)
            .into(itemView.findViewById(R.id.image_list_item_logo))
        bundle = bundleOf("modele_name" to model.name,"modele_id" to model.id,"brand_id"
            to model.id_marque,"brand_name" to brand_name
        ,"colors" to colorToString(colorList))


        this.itemView.setOnClickListener(


            Navigation.createNavigateOnClickListener(R.id.modelFragment,bundle)
        )

    }

    fun colorToString (colors_list:List<PaintColor>):List<String>
    {
        var list:ArrayList<String> = ArrayList<String>()
        for (color:PaintColor in colors_list)
        {
            list.add(color.hexCode)
        }

        return list
    }


}