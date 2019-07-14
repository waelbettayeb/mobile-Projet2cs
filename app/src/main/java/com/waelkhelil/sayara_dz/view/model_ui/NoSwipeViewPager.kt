package com.waelkhelil.sayara_dz.view.model_ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class NoSwipeViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {
    private var pagingEnabled: Boolean = false

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (this.pagingEnabled) {
            super.onTouchEvent(event)
        } else false
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (this.pagingEnabled) {
            super.onInterceptTouchEvent(event)
        } else false
    }

}