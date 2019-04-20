package com.waelkhelil.sayara_dz.view.model_ui.cards

import android.graphics.Bitmap
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.view.model_ui.utils.DecodeBitmapTask

class SliderCard(itemView: View) : RecyclerView.ViewHolder(itemView), DecodeBitmapTask.Listener {

    private val imageView: ImageView

    private var task: DecodeBitmapTask? = null

    init {
        imageView = itemView.findViewById<View>(R.id.image) as ImageView
    }

    internal fun setContent(@DrawableRes resId: Int) {
        if (viewWidth == 0) {
            itemView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    itemView.viewTreeObserver.removeOnGlobalLayoutListener(this)

                    viewWidth = itemView.width
                    viewHeight = itemView.height
                    loadBitmap(resId)
                }
            })
        } else {
            loadBitmap(resId)
        }
    }

    internal fun clearContent() {
        task?.cancel(true)
    }

    private fun loadBitmap(@DrawableRes resId: Int) {
        task = DecodeBitmapTask(itemView.resources, resId, viewWidth, viewHeight, this)
        task!!.execute()
    }

    override fun onPostExecuted(bitmap: Bitmap) {
        imageView.setImageBitmap(bitmap)
    }

    companion object {

        private var viewWidth = 0
        private var viewHeight = 0
    }

}