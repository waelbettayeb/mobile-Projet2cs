package com.waelkhelil.sayara_dz.view.brand_ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView
import com.waelkhelil.sayara_dz.database.model.Model
import com.waelkhelil.sayara_dz.database.model.PaintColor

class ModelsListItemAdapter(private val list: List<Model>,private val brand_name:String,context:Fragment,viewModel:ModelViewModel)
    : RecyclerView.Adapter<ModelItemViewHolder>() , FastScrollRecyclerView.SectionedAdapter{
    var models:MutableList<Model>
    lateinit  var context:Fragment
    lateinit  var viewModel:ModelViewModel
    init {
        this.context=context
        this.viewModel=viewModel
        models = list.sortedBy { model: Model -> model.name }.toMutableList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ModelItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ModelItemViewHolder, position: Int) {
        val model: Model = models[position]
        viewModel.initColor(model.id)
        viewModel.getModelColors()!!.observe(context, Observer<List<PaintColor>>
        {

            holder.bind(model,brand_name,it)
            Log.i("from  adapter","${it.size}")
        })





    }

    override fun getItemCount(): Int = list.size
    override fun getSectionName(position: Int): String {
        return models[position].name[0].toString()
    }

    fun getModelColors(model_id:String):List<PaintColor>
    {   var colorList:List<PaintColor> = emptyList()
        viewModel.initColor(model_id)
            viewModel.getModelColors()!!.observe(context, Observer<List<PaintColor>>
            {

               colorList=it
            })

        Log.i("from  adapter","${colorList.size}")

             return colorList
    }
}