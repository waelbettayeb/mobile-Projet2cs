package com.waelkhelil.sayara_dz.view.model_ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextSwitcher
import android.widget.TextView
import android.widget.ViewSwitcher
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.ramotion.cardslider.CardSliderLayoutManager
import com.ramotion.cardslider.CardSnapHelper
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.SharedViewModel
import com.waelkhelil.sayara_dz.database.model.Version
import com.waelkhelil.sayara_dz.view.compare.CompareFragment
import com.waelkhelil.sayara_dz.view.model_ui.cards.SliderAdapter
import com.google.android.material.snackbar.Snackbar
import com.waelkhelil.sayara_dz.database.model.Option
import kotlinx.android.synthetic.main.fragment_model_versions.*


class ModelVersionsFragment : Fragment() {

    companion object {
        fun newInstance() = ModelVersionsFragment()
    }

    private lateinit var sharedViewModel: SharedViewModel
    private val list:List<Version> = listOf(
        Version(
            0,
            "Zen",
            "https://www.cdn.renault.com/content/dam/Renault/FR/personal-cars/clio/CLIO%20V/PackshotsVersions/" +
                    "New_Clio_Zen_Gris_Titanium.jpeg.ximg.l_12_m.smart.jpeg",
            "2 300 K",
            setOf()
        ),
        Version(
            1,
            "Intens",
            "https://www.cdn.renault.com/content/dam/Renault/FR/personal-cars/clio/CLIO%20V/PackshotsVersions/" +
                    "New_Clio_Intens_Orange_Valencia_Jantes.jpg.ximg.l_12_m.smart.jpg",
            "2 700 K",
            setOf(Option(0, "option_00",0),Option(1, "option_01",1))
        ),
        Version(
            2,
            "RS Line",
            "https://www.cdn.renault.com/content/dam/Renault/FR/personal-cars/clio/CLIO%20V/PackshotsVersions/" +
                    "New_Clio_RS_Line_Bleu_Iron.jpeg.ximg.l_12_m.smart.jpeg",
            "3 200 K",
            setOf(Option(0, "option_00",0),Option(1, "option_01",1),Option(2, "option_02",2))
        ),
        Version(
            3,
            "Initial Paris",
            "https://www.cdn.renault.com/content/dam/Renault/FR/personal-cars/clio/CLIO%20V/PackshotsVersions/" +
                    "Nouvelle_CLIO_Initiale_Paris_packshot_grade.jpeg.ximg.l_12_m.smart.jpeg",
            "3 600 K",
            setOf(Option(0, "option_00",0),Option(1, "option_01",1),Option(2, "option_02",2),
                Option(3, "option_03",3))
        )
    )
    private val sliderAdapter = SliderAdapter(list,  OnCardClickListener())

    private var layoutManager: CardSliderLayoutManager? = null
    private var recyclerView: RecyclerView? = null
    private var priceSwitcher: TextSwitcher? = null

    private var version1TextView: TextView? = null
    private var version2TextView: TextView? = null
    private var versionOffset1: Float = 0F
    private var versionOffset2: Float = 0F
    private var versionAnimDuration: Long = 0
    private var currentPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Share data between fragments
        sharedViewModel = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

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
        initVersionText()
        initSwitchers()

        val lButtonCompare= getView()!!.
            findViewById<Button>(R.id.button_compare_button)
        lButtonCompare.setOnClickListener {
            sharedViewModel.addToCompareList(list[currentPosition])
            val contextView : View = view.findViewById(R.id.layout_version)

            if (sharedViewModel.mCompareList.value!!.size > 1)
                Snackbar.make(contextView, R.string.msg_added_to_comparison_list, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.see_all) {
                        val lCompareFragment= CompareFragment()
                        fragmentManager?.let { it1 -> lCompareFragment.show(it1, CompareFragment.TAG) }
                    }.show()
            else
                Snackbar.make(view, R.string.msg_added_to_comparison_list, Snackbar.LENGTH_SHORT)
                    .show()
        }
        button_command_version.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            val bundle = Bundle()
            bundle.putInt("versionId", currentVersion().id)
            navController.navigate(R.id.configureVersionFragment, bundle)
        }
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
        priceSwitcher?.setCurrentText(list[0].price)

    }

    private fun currentVersion():Version{
        return list[currentPosition]
    }

    private fun initVersionText() {
        versionAnimDuration = resources.getInteger(R.integer.labels_animation_duration).toLong()
        versionOffset1 = resources.getDimensionPixelSize(R.dimen.left_offset).toFloat()
        versionOffset2 = resources.getDimensionPixelSize(R.dimen.card_width).toFloat()

        version1TextView = view?.findViewById(R.id.tv_country_1) as TextView
        version2TextView = view?.findViewById(R.id.tv_country_2) as TextView

        version1TextView?.x = versionOffset1
        version2TextView?.x = versionOffset2

        version1TextView?.text = list[currentPosition].name
        version2TextView?.alpha = 0f

    }


    private fun setVersionText(text: String, left2right: Boolean) {
        val invisibleText: TextView
        val visibleText: TextView
        if ((version2TextView != null) && (version1TextView != null)) {
            val alpha1 = (version1TextView as TextView).alpha
            val alpha2 = (version2TextView as TextView).alpha
            if (alpha1 > alpha2) {
                visibleText = version1TextView as TextView
                invisibleText = version2TextView as TextView
            } else {
                visibleText = version2TextView as TextView
                invisibleText = version1TextView as TextView
            }


            val vOffset: Float
            if (left2right) {
                invisibleText.x = 0f
                vOffset = versionOffset2
            } else {
                invisibleText.x = versionOffset2
                vOffset = 0f
            }
            invisibleText.text = text

            val iAlpha = ObjectAnimator.ofFloat(invisibleText, "alpha", 1f)
            val vAlpha = ObjectAnimator.ofFloat(visibleText, "alpha", 0f)
            val iX = ObjectAnimator.ofFloat(invisibleText, "x", versionOffset1)
            val vX = ObjectAnimator.ofFloat(visibleText, "x", vOffset)

            val animSet = AnimatorSet()
            animSet.playTogether(iAlpha, vAlpha, iX, vX)
            animSet.duration = versionAnimDuration
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

        setVersionText(list[pos % list.size].name, left2right)

        priceSwitcher?.setInAnimation(context, animH[0])
        priceSwitcher?.setOutAnimation(context, animH[1])
        priceSwitcher?.setText(list[pos % list.size].price)



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
