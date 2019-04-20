package com.waelkhelil.sayara_dz.view.model_ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextSwitcher
import android.widget.TextView
import android.widget.ViewSwitcher
import androidx.annotation.StyleRes
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ramotion.cardslider.CardSliderLayoutManager
import com.ramotion.cardslider.CardSnapHelper
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.view.model_ui.cards.SliderAdapter


class ModelVersionsFragment : Fragment() {

    companion object {
        fun newInstance() = ModelVersionsFragment()
    }

    private val dotCoords = Array(5) { IntArray(2) }
    private val pics = intArrayOf(R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5)

    private val descriptions =
        intArrayOf(R.string.text1, R.string.text2, R.string.text3, R.string.text4, R.string.text5)
    private val countries = arrayOf("PARIS", "SEOUL", "LONDON", "BEIJING", "THIRA")
    private val places = arrayOf("The Louvre", "Gwanghwamun", "Tower Bridge", "Temple of Heaven", "Aegeana Sea")
    private val prices = arrayOf("2 300 K", "2 300 K", "2 300 K")
    private val times =
        arrayOf("Aug 1 - Dec 15    7:00-18:00", "Sep 5 - Nov 10    8:00-16:00", "Mar 8 - May 21    7:00-18:00")

    private val sliderAdapter = SliderAdapter(pics, 20, OnCardClickListener())

    private var layoutManager: CardSliderLayoutManager? = null
    private var recyclerView: RecyclerView? = null
    private var priceSwitcher: TextSwitcher? = null
    private var placeSwitcher: TextSwitcher? = null
    private var clockSwitcher: TextSwitcher? = null
    private var descriptionsSwitcher: TextSwitcher? = null
    private var greenDot: View? = null

    private var country1TextView: TextView? = null
    private var country2TextView: TextView? = null
    private var countryOffset1: Float = 0F
    private var countryOffset2: Float = 0F
    private var countryAnimDuration: Long = 0
    private var currentPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_model_versions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = context?.let { CardSliderLayoutManager(it) }


        initRecyclerView()
        initCountryText()
        initSwitchers()
    }
    private fun initRecyclerView() {

        recyclerView = view?.findViewById(R.id.recycler_view_versions) as RecyclerView
        recyclerView?.adapter = sliderAdapter
        recyclerView?.layoutManager = layoutManager
        recyclerView?.setHasFixedSize(true)

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    onActiveCardChange()
                }
            }
        })

        layoutManager = recyclerView?.layoutManager as CardSliderLayoutManager?

        CardSnapHelper().attachToRecyclerView(recyclerView)
    }
    private fun initSwitchers() {
        priceSwitcher = view?.findViewById(R.id.ts_price)
        priceSwitcher?.setFactory(TextViewFactory(R.style.price_textView, true))
        priceSwitcher?.setCurrentText(prices[0])

        placeSwitcher = view?.findViewById(R.id.ts_place)
        placeSwitcher?.setFactory(TextViewFactory(R.style.PlaceTextView, false))
        placeSwitcher?.setCurrentText(places[0])

        clockSwitcher = view?.findViewById(R.id.ts_clock)
        clockSwitcher?.setFactory(TextViewFactory(R.style.ClockTextView, false))
        clockSwitcher?.setCurrentText(times[0])

        descriptionsSwitcher = view?.findViewById(R.id.ts_description)
        descriptionsSwitcher?.setInAnimation(context, android.R.anim.fade_in)
        descriptionsSwitcher?.setOutAnimation(context, android.R.anim.fade_out)
        descriptionsSwitcher?.setFactory(TextViewFactory(R.style.DescriptionTextView, false))
        descriptionsSwitcher?.setCurrentText(getString(descriptions[0]))

    }

    private fun initCountryText() {
        countryAnimDuration = resources.getInteger(R.integer.labels_animation_duration).toLong()
        country1TextView = view?.findViewById(R.id.tv_country_1) as TextView
        country2TextView = view?.findViewById(R.id.tv_country_2) as TextView

        country1TextView?.x = countryOffset1
        country2TextView?.x = countryOffset2
        country1TextView?.text = countries[0]
        country2TextView?.alpha = 0f

    }


    private fun setCountryText(text: String, left2right: Boolean) {
        val invisibleText: TextView
        val visibleText: TextView
        if ((country2TextView != null) && (country1TextView != null)) {
            val alpha1 = (country1TextView as TextView).alpha
            val alpha2 = (country2TextView as TextView).alpha
            if (alpha1 > alpha2) {
                visibleText = country1TextView as TextView
                invisibleText = country2TextView as TextView
            } else {
                visibleText = country2TextView as TextView
                invisibleText = country1TextView as TextView
            }


            val vOffset: Float
            if (left2right) {
                invisibleText.x = 0f
                vOffset = countryOffset2
            } else {
                invisibleText.x = countryOffset2
                vOffset = 0F
            }

            invisibleText.text = text

            val iAlpha = ObjectAnimator.ofFloat(invisibleText, "alpha", 1f)
            val vAlpha = ObjectAnimator.ofFloat(visibleText, "alpha", 0f)
            val iX = ObjectAnimator.ofFloat(invisibleText, "x", countryOffset1)
            val vX = ObjectAnimator.ofFloat(visibleText, "x", vOffset)

            val animSet = AnimatorSet()
            animSet.playTogether(iAlpha, vAlpha, iX, vX)
            animSet.duration = countryAnimDuration
            animSet.start()
        }
    }

    private fun onActiveCardChange() {
        val pos = layoutManager?.activeCardPosition
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return
        }

        if (pos != null) {
            onActiveCardChange(pos)
        }
    }

    private fun onActiveCardChange(pos: Int) {
        val animH = intArrayOf(R.anim.slide_in_right, R.anim.slide_out_left)
        val animV = intArrayOf(R.anim.slide_in_top, R.anim.slide_out_bottom)

        val left2right = pos < currentPosition
        if (left2right) {
            animH[0] = R.anim.slide_in_left
            animH[1] = R.anim.slide_out_right

            animV[0] = R.anim.slide_in_bottom
            animV[1] = R.anim.slide_out_top
        }

        setCountryText(countries[pos % countries.size], left2right)

        priceSwitcher?.setInAnimation(context, animH[0])
        priceSwitcher?.setOutAnimation(context, animH[1])
        priceSwitcher?.setText(prices[pos % prices.size])

        placeSwitcher?.setInAnimation(context, animV[0])
        placeSwitcher?.setOutAnimation(context, animV[1])
        placeSwitcher?.setText(places[pos % places.size])

        clockSwitcher?.setInAnimation(context, animV[0])
        clockSwitcher?.setOutAnimation(context, animV[1])
        clockSwitcher?.setText(times[pos % times.size])

        descriptionsSwitcher?.setText(getString(descriptions[pos % descriptions.size]))


        greenDot?.let {
            ViewCompat.animate(it)
                .translationX(dotCoords[pos % dotCoords.size][0].toFloat())
                .translationY(dotCoords[pos % dotCoords.size][1].toFloat())
                .start()
        }

        currentPosition = pos
    }

  
//
    private inner class TextViewFactory internal constructor(
        @param:StyleRes @field:StyleRes
        internal val styleId: Int, internal val center: Boolean
    ) : ViewSwitcher.ViewFactory {

        override fun makeView(): View {
            val textView = TextView(context)

            if (center) {
                textView.gravity = Gravity.CENTER
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextAppearance(styleId)
            }

            return textView
        }

    }

    private inner class OnCardClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            val lm = recyclerView?.layoutManager as CardSliderLayoutManager?

            if (lm!!.isSmoothScrolling) {
                return
            }

            val activeCardPosition = lm.activeCardPosition
            if (activeCardPosition == RecyclerView.NO_POSITION) {
                return
            }

            val clickedPosition = recyclerView?.getChildAdapterPosition(view)
            if ((clickedPosition != activeCardPosition) && (clickedPosition != null)) {
                if (clickedPosition > activeCardPosition) {
                    recyclerView?.smoothScrollToPosition(clickedPosition)
                    onActiveCardChange(clickedPosition)
                }
            }
        }
    }
}
