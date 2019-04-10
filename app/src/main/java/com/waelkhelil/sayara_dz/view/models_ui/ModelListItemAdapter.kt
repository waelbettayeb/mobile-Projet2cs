package com.waelkhelil.sayara_dz.view.models_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.sayara_dz.model.Model

class ModelListItemAdapter(private val list: List<Model>)
    : RecyclerView.Adapter<ModelItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ModelItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ModelItemViewHolder, position: Int) {
        val model: Model = list[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int = list.size

}