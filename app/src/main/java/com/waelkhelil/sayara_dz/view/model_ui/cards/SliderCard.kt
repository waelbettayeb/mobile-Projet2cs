package com.waelkhelil.sayara_dz.view.model_ui.cards

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.model.Version

class SliderCard(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageView: ImageView = itemView.findViewById<View>(R.id.image) as ImageView

    internal fun setContent(pVersion: Version) {
        Glide
            .with(itemView)
            .load("https://www.automobile-magazine.fr/asset/cms/650x407/153411/config/106932/plutot-conservatrice-a-lexterieur-la-renault-clio-5-change-en-revanche-totalement-dans-lhabitacle.jpg")
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.icon_mono)
            .into(imageView)

    }

}