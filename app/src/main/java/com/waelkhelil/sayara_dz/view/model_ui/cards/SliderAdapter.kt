package com.waelkhelil.sayara_dz.view.model_ui.cards


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.sayara_dz.R

class SliderAdapter(
    private val content: IntArray,
    private val count: Int,
    private val listener: View.OnClickListener?
) : RecyclerView.Adapter<SliderCard>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderCard {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.layout_slider_card, parent, false)

        if (listener != null) {
            view.setOnClickListener { view -> listener.onClick(view) }
        }

        return SliderCard(view)
    }

    override fun onBindViewHolder(holder: SliderCard, position: Int) {
        holder.setContent(content[position % content.size])
    }

    override fun onViewRecycled(holder: SliderCard) {
        holder.clearContent()
    }

    override fun getItemCount(): Int {
        return count
    }

}
