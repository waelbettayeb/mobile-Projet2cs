package com.waelkhelil.sayara_dz.view.model_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.sayara_dz.R

class OptionListAdapter(private val list: List<String>)
    : RecyclerView.Adapter<OptionListAdapter.OptionViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return OptionViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        val lOption = list[position]
        holder.bind(lOption)
    }

    override fun getItemCount(): Int = list.size

    inner class OptionViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.option_list_item, parent, false)){
        private lateinit var mOption: String

        fun bind(pOption: String) {
            mOption = pOption
            itemView.findViewById<TextView>(R.id.tv_option_description).text = pOption
        }

    }
}