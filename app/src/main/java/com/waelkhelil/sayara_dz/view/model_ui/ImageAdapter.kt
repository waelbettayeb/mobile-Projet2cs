package com.waelkhelil.sayara_dz.view.model_ui

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.sayara_dz.R

class ImageAdapter(private val list: List<Bitmap>)
    : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImageViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val lBitmap: Bitmap = list[position]
        holder.bind(lBitmap)
    }

    override fun getItemCount(): Int = list.size
    inner class ImageViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_image, parent, false)){
        private var mBitmap: Bitmap? = null
        private var mImageView : ImageView? = null


        init {
            mImageView = itemView.findViewById(R.id.imageView)
        }

        fun bind(Bitmap: Bitmap) {
            mBitmap = Bitmap
            mImageView?.setImageBitmap(mBitmap)
        }
    }
}