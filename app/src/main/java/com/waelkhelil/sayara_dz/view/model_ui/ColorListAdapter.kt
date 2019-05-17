package com.waelkhelil.sayara_dz.view.model_ui

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
import com.waelkhelil.sayara_dz.database.model.PaintColor

class ColorListAdapter(private val list: List<PaintColor>)
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
        private lateinit var mPaintColor: PaintColor

        fun bind(pPaintColor: PaintColor) {
            this.mPaintColor = pPaintColor

            val bitmap: Bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            var left = 0
            var top = 0
            var right = 500
            var bottom = 500
            var shapeDrawable = ShapeDrawable(OvalShape())
            shapeDrawable.setBounds( left, top, right, bottom)
            shapeDrawable.paint.color = Color.parseColor(mPaintColor.hexCode)
            shapeDrawable.draw(canvas)
            val image_color = itemView.findViewById<ImageView>(R.id.imageV)
            image_color.background = BitmapDrawable(bitmap)

        }

    }
}