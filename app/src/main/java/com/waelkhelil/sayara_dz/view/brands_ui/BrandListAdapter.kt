package com.waelkhelil.sayara_dz.view.brands_ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.model.Brand

class BrandListAdapter internal constructor(context: Context) : RecyclerView.Adapter<BrandListAdapter.BrandViewHolder>()
{
    val appContext: Context=context
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var brands = emptyList<Brand>()

    inner class BrandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitleView : TextView = itemView.findViewById(R.id.text_list_item_name)
        val mLogoView : ImageView = itemView.findViewById(R.id.image_list_item_logo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val itemView = inflater.inflate(R.layout.list_item_brand, parent, false)
        return BrandViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val current = brands[position]
        holder.mTitleView.text = current.name
        Picasso.with(appContext).load(current.logo).into( holder.mLogoView)

    }

    internal fun setBrands(brands: List<Brand>) {

        this.brands= brands
        notifyDataSetChanged()


    }

    override fun getItemCount() = brands.size
}