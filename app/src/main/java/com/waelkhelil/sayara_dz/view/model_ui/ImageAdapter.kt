package com.waelkhelil.sayara_dz.view.model_ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.waelkhelil.sayara_dz.R

class ImageAdapter(private val list: List<String>)
    : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImageViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val lUrl: String = list[position]
        holder.bind(lUrl)
    }

    override fun getItemCount(): Int = list.size
    inner class ImageViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_image, parent, false)){
        private lateinit var mUrl: String
        private var mImageView : ImageView

        init {
            mImageView = itemView.findViewById(R.id.imageView)
        }

        fun bind(pUrl: String) {
            this.mUrl = pUrl
            Glide
                .with(itemView)
                .load(mUrl)
                .placeholder(R.drawable.icon_mono)
                .into(mImageView)
        }
    }
}