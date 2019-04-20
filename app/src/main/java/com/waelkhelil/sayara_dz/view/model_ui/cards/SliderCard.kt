package com.waelkhelil.sayara_dz.view.model_ui.cards

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.model.Version
import com.waelkhelil.sayara_dz.view.model_ui.utils.DecodeBitmapTask

class SliderCard(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageView: ImageView = itemView.findViewById<View>(R.id.image) as ImageView

    internal fun setContent(pVersion: Version) {
        Glide
            .with(itemView)
            .load(pVersion.url)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.icon_mono)
            .into(imageView)

    }

}