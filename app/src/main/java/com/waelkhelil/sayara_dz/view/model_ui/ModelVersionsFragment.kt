package com.waelkhelil.sayara_dz.view.model_ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ramotion.cardslider.CardSliderLayoutManager
import com.ramotion.cardslider.CardSnapHelper
import com.waelkhelil.sayara_dz.SharedViewModel
import com.waelkhelil.sayara_dz.database.Injection
import com.waelkhelil.sayara_dz.database.model.*
import com.waelkhelil.sayara_dz.view.brand_ui.ModelViewModel
import com.waelkhelil.sayara_dz.view.compare.CompareFragment
import com.waelkhelil.sayara_dz.view.model_ui.cards.SliderAdapter

import com.waelkhelil.sayara_dz.database.model.Option
import com.waelkhelil.sayara_dz.view.configure_version.ConfigureDialogFragment
import kotlinx.android.synthetic.main.fragment_model_versions.*






class ModelVersionsFragment : Fragment() {

    companion object {
        fun newInstance(modele_id:String,brand_id:String) = ModelVersionsFragment().apply {
                   this.modele_id = modele_id
                   this.brand_id = brand_id

        }
    }

    private lateinit var versionViewModel: ModelVersionsViewModel
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var viewModel: ModelViewModel
     private lateinit  var modele_id: String
     private lateinit var brand_id: String
    private lateinit var sliderAdapter: SliderAdapter

    private var layoutManager: CardSliderLayoutManager? = null
    private var recyclerView: RecyclerView? = null
    private var priceSwitcher: TextSwitcher? = null
    private lateinit var colorList: ArrayList<PaintColor>
    private var optionsList: ArrayList<Option> = ArrayList<Option>()
    private lateinit var versionList: List<Version>
    private var version1TextView: TextView? = null
    private var version2TextView: TextView? = null
    private var versionOffset1: Float = 0F
    private var versionOffset2: Float = 0F
    private var versionAnimDuration: Long = 0
    private var currentPosition: Int = 0
    private var orderTotalPrice: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Share data between fragments


        sharedViewModel = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        versionViewModel = ViewModelProviders.of(this, Injection.provideModelViewModelFactory(modele_id))
            .get(ModelVersionsViewModel::class.java)

        initAdapter()

        //getting colors
        viewModel = ViewModelProviders.of(this, Injection.provideModelViewModelFactory("1"))
            .get(ModelViewModel::class.java)
        viewModel.init()
        viewModel.initColor(modele_id)

        viewModel.getModelColors()!!.observe(this, Observer<List<PaintColor>>
        {

            colorList = it as ArrayList<PaintColor>
        })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.waelkhelil.sayara_dz.R.layout.fragment_model_versions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = context?.let { CardSliderLayoutManager(it) }
        initSwitchers()




        button_command_version.setOnClickListener {
            showColorsDialog(colorList, optionsList)
        }
        val lButtonCompare = getView()!!.findViewById<Button>(com.waelkhelil.sayara_dz.R.id.button_compare_button)
        lButtonCompare.setOnClickListener {
            sharedViewModel.addToCompareList(versionList[currentPosition])
            val contextView: View = view.findViewById(com.waelkhelil.sayara_dz.R.id.layout_version)

            if (sharedViewModel.mCompareList.value!!.size > 1)
                Snackbar.make(
                    contextView,
                    com.waelkhelil.sayara_dz.R.string.msg_added_to_comparison_list,
                    Snackbar.LENGTH_SHORT
                )
                    .setAction(com.waelkhelil.sayara_dz.R.string.see_all) {
                        val lCompareFragment = CompareFragment()
                        fragmentManager?.let { it1 -> lCompareFragment.show(it1, CompareFragment.TAG) }
                    }.show()
            else
                Snackbar.make(
                    view,
                    com.waelkhelil.sayara_dz.R.string.msg_added_to_comparison_list,
                    Snackbar.LENGTH_SHORT
                )
                    .show()
        }
        button_command_version.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("versionId", versionList[currentPosition].id)

            val dialog = ConfigureDialogFragment()
            dialog.arguments = bundle

            val ft = fragmentManager!!.beginTransaction()
            dialog.show(ft, ConfigureDialogFragment.TAG)
        }
    }

    private fun initAdapter() {

        versionViewModel.init()
        versionViewModel.getRepository()!!.observe(this.activity!!, Observer<List<Version>> {
            sliderAdapter = SliderAdapter(it, OnCardClickListener())
            if (it.size != 0) {

                initRecyclerView(it)
                initVersionText(it)

            }

            versionViewModel.getNetworkErrors()!!.observe(this.activity!!, Observer<String> {
                Log.i("errorrr", "here")
                Toast.makeText(this.activity, com.waelkhelil.sayara_dz.R.string.brands_error, Toast.LENGTH_SHORT).show()
            })
        })


    }

    private fun initRecyclerView(list: List<Version>) {
        this.versionList = list
        recyclerView = view?.findViewById(com.waelkhelil.sayara_dz.R.id.recycler_view_versions) as RecyclerView
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
        priceSwitcher = view?.findViewById(com.waelkhelil.sayara_dz.R.id.ts_price)
        priceSwitcher?.setFactory(TextViewFactory(com.waelkhelil.sayara_dz.R.style.price_textView, true))
        priceSwitcher?.background = resources.getDrawable(com.waelkhelil.sayara_dz.R.color.white)


    }

    /*private fun currentVersion():Version{
        return list[currentPosition]
    }*/

    private fun initVersionText(list: List<Version>) {
        versionAnimDuration =
            resources.getInteger(com.waelkhelil.sayara_dz.R.integer.labels_animation_duration).toLong()
        versionOffset1 = resources.getDimensionPixelSize(com.waelkhelil.sayara_dz.R.dimen.left_offset).toFloat()
        versionOffset2 = resources.getDimensionPixelSize(com.waelkhelil.sayara_dz.R.dimen.card_width).toFloat()

        version1TextView = view?.findViewById(com.waelkhelil.sayara_dz.R.id.tv_country_1) as TextView
        version2TextView = view?.findViewById(com.waelkhelil.sayara_dz.R.id.tv_country_2) as TextView

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
        val animH =
            intArrayOf(com.waelkhelil.sayara_dz.R.anim.slide_in_right, com.waelkhelil.sayara_dz.R.anim.slide_out_left)
        val animV =
            intArrayOf(com.waelkhelil.sayara_dz.R.anim.slide_in_top, com.waelkhelil.sayara_dz.R.anim.slide_out_bottom)

        val left2right = pos < currentPosition
        if (left2right) {
            animH[0] = com.waelkhelil.sayara_dz.R.anim.slide_in_left
            animH[1] = com.waelkhelil.sayara_dz.R.anim.slide_out_right

            animV[0] = com.waelkhelil.sayara_dz.R.anim.slide_in_bottom
            animV[1] = com.waelkhelil.sayara_dz.R.anim.slide_out_top
        }



        priceSwitcher?.setInAnimation(context, animH[0])
        priceSwitcher?.setOutAnimation(context, animH[1])
        setVersionText(versionList[pos % versionList.size].name, left2right)


        var price = ""
        versionViewModel.getVersionPrice(versionList[pos % versionList.size].id)!!.observe(
            this.activity!!,
            Observer<List<VersionPrice>> {

                if (it.size != 0) {

                    price = it.get(0).price
                    Log.i("price", "${price}")


                } else {
                    price = "notloaded"
                }



                priceSwitcher?.setCurrentText(price)

                versionViewModel.getNetworkErrors()!!.observe(this.activity!!, Observer<String> {

                    Toast.makeText(this.activity, com.waelkhelil.sayara_dz.R.string.brands_error, Toast.LENGTH_SHORT)
                        .show()
                })
            })
        versionViewModel.getVersionOptions((versionList[pos % versionList.size]).id)
            .observe(this, Observer<List<Option>>
            {

                optionsList = it as ArrayList<Option>
            })




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


    @SuppressLint("ResourceType")
    private fun showColorsDialog(colorList: ArrayList<PaintColor>, optionsList: ArrayList<Option>) {
        orderTotalPrice = 0.0f
        var dialogs = this.context?.let { Dialog(it) }
        var selectedOPtions: ArrayList<String> = ArrayList<String>()
        dialogs!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogs.setCancelable(false)

        dialogs.setContentView(com.waelkhelil.sayara_dz.R.layout.order_alert)

        val yesBtn = dialogs.findViewById(com.waelkhelil.sayara_dz.R.id.btn_ok) as Button
        val filterSpinner = dialogs.findViewById(com.waelkhelil.sayara_dz.R.id.filtres) as Spinner
        val cb = arrayOfNulls<CheckBox>(optionsList.size)


        // Creating adapter forf spinner
        val dataAdapter = ArrayAdapter<PaintColor>(this.context!!, android.R.layout.simple_spinner_item, colorList!!)


        // attaching data adapter to spinne
        filterSpinner.setAdapter(dataAdapter)

        //adding options check boxes dynamically
        var i = 0
        val ll = dialogs.findViewById(com.waelkhelil.sayara_dz.R.id.options_layout) as LinearLayout
        if (optionsList.size != 0) {

            for (j in optionsList) {
                cb[i] = CheckBox(this.activity!!)
                ll.addView(cb[i])
                cb[i]!!.setText(optionsList[i].name)
                cb[i]!!.setId(i)
                i++
            }

        } else {
            (dialogs.findViewById(com.waelkhelil.sayara_dz.R.id.tv_choose_option) as TextView).visibility =
                View.INVISIBLE
        }

        yesBtn.setOnClickListener {

            i = 0
            for (j in optionsList) {
                if (cb[i]!!.isChecked) {

                    selectedOPtions.add(optionsList[i].id)
                    i++
                }

            }
/*
versionViewModel.getColorPrice((colorList[filterSpinner.selectedItemPosition].code).toString()).observe(this, Observer<List<ColorPrice>>
 {

     if (it.isNotEmpty()){

         Log.i("color",it[0].price.toString())
     }


 })*/


            // Log.i("color",getVersionPrice(  colorList[filterSpinner.selectedItemPosition].code).toString())

            OrderCar(
                selectedOPtions,
                versionList[currentPosition].id,
                colorList[filterSpinner.selectedItemPosition].code
            )



            dialogs.hide()
        }
        dialogs.show()

    }


    fun getVersionPrice(id_version: String): Float {
        var version_price: Float = 0.0F
        versionViewModel.getVersionPrice(id_version).observe(this, Observer<List<VersionPrice>>
        {

            if (it.isNotEmpty()) {
                version_price = it[0].price.toFloat()

            }


        })
        return version_price
    }

    fun OrderCar(neededOptions: List<String>, id_version: String, id_color: String) {


        var options_price: MutableLiveData<Float>? = MutableLiveData(0.0f)
        var price_color: MutableLiveData<Float>? = MutableLiveData(0.0f)
        var price_version: Float = 0.0f


        versionViewModel.getVersionPrice(id_version).observe(this.activity!!, Observer<List<VersionPrice>>
        {

            if (it.isNotEmpty()) {
                orderTotalPrice = orderTotalPrice + it[0].price.toFloat()

                Log.i("versionprrice", "${it[0].price.toFloat()}")


            }

            for (item in neededOptions) {
                versionViewModel.getOptionPrice(item).observe(this, Observer<List<OptionPrice>>
                {

                    if (it.size != 0) {

                        orderTotalPrice = orderTotalPrice + it[0].price.toFloat()
                    }

                    Log.i("optionsprice", "${orderTotalPrice}")



                })}
            versionViewModel.getColorPrice(id_color).observe(this, Observer<List<ColorPrice>>
            {

                if (it.isNotEmpty()) {
                    orderTotalPrice = orderTotalPrice + it[0].price.toFloat()

                }

                versionViewModel.checkAvailable(
                    brand_id, modele_id, versionList[currentPosition].id, id_color,
                    neededOptions
                    , orderTotalPrice
                )


                //Toast.makeText(this.activity,"${msg}",Toast.LENGTH_SHORT).show()
                versionViewModel.getOrderMessage()!!.observe(this.activity!!, Observer<String> {

                    Toast.makeText(this.activity, it, Toast.LENGTH_SHORT).show()

                })
            })















        })


    }
}