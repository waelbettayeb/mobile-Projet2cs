package com.waelkhelil.sayara_dz.view.models_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView
import com.waelkhelil.sayara_dz.model.Model

class ModelListItemAdapter(private val list: List<Model>)
    : RecyclerView.Adapter<ModelItemViewHolder>() , FastScrollRecyclerView.SectionedAdapter{
    var models:MutableList<Model>
    init {
        models = list.sortedBy { model: Model -> model.name }.toMutableList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ModelItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ModelItemViewHolder, position: Int) {
        val model: Model = models[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int = list.size
    override fun getSectionName(position: Int): String {
        return models[position].name[0].toString()
    }
}