package com.waelkhelil.sayara_dz.model_ui

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.sayara_dz.R

class ColorListAdapter(private val list: List<String>)
    : RecyclerView.Adapter<ColorListAdapter.ColorViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ColorViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val lColor = list[position]
        holder.bind(lColor)
    }

    override fun getItemCount(): Int = list.size

    inner class ColorViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.color_view, parent, false)){
        private lateinit var mPaintColor: String

        fun bind(pPaintColor: String) {
            this.mPaintColor = pPaintColor

            val bitmap: Bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val left = 0
            val top = 0
            val right = 500
            val bottom = 500
            val shapeDrawable = ShapeDrawable(OvalShape())
            shapeDrawable.setBounds( left, top, right, bottom)
            shapeDrawable.paint.color = Color.parseColor("#".plus(mPaintColor))
            shapeDrawable.draw(canvas)
            val image_color = itemView.findViewById<ImageView>(R.id.imageV)
            image_color.background = BitmapDrawable(bitmap)

        }

    }
}