package com.waelkhelil.sayara_dz.view.models_ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.sayara_dz.database.Brand
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.Model
import com.waelkhelil.sayara_dz.view.MainActivity
import com.waelkhelil.sayara_dz.view.models_ui.ModelFragment

class ModelItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.model_list_item, parent, false)), View.OnClickListener  {
    //TODO : convert this class to a generic class
    private var mModel: Model? = null
    private var mTitleView: TextView? = null


    init {
        mTitleView = itemView.findViewById(R.id.text_card_header)
        this.itemView.setOnClickListener(this)
    }

    fun bind(model: Model) {
        mTitleView?.text = model.name
        mModel = model
    }

    override fun onClick(v: View?) {
        Log.d(toString(), "onClick")
        val lMainActivity = this.itemView.context as MainActivity

//        val lBundle= Bundle()
//        val lFragment = ModelFragment()
//
//        mModel?.id?.let { lBundle.putLong("model_id", it) }
//        lFragment.arguments = lBundle
//
//        lMainActivity.setFragment(lFragment, "version", null)
    }
}