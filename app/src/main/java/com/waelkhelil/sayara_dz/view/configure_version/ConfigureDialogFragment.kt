package com.waelkhelil.sayara_dz.view.configure_version

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.Injection
import com.waelkhelil.sayara_dz.database.model.Option
import com.waelkhelil.sayara_dz.database.model.PaintColor
import com.waelkhelil.sayara_dz.view.model_ui.ModelVersionsViewModel
import kotlinx.android.synthetic.main.fragment_configure_version.*
import java.lang.Double.parseDouble


class ConfigureDialogFragment : DialogFragment() {


    companion object {
        const val TAG: String = "ConfigureDialogFragment"
        fun newInstance() = ConfigureDialogFragment()

    }
    lateinit  var colorsList: MutableLiveData<List<PaintColor>>
    lateinit  var optionsList:MutableLiveData<List<Option>>
    private  var versionPrice:String?="0"
    private var totalPrice:Int?=0
    private lateinit var  versionId:String
    private lateinit var  modeleId:String
    private lateinit var  brandId:String
    private lateinit var carId:String
    private lateinit var versionViewModel: ModelVersionsViewModel
    private var chosenColor:String =""
    private var chosenOptions:ArrayList<String> = ArrayList<String>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_configure_version, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val versionName = arguments?.getString("versionName")
         versionPrice = arguments?.getString("versionPrice")
        versionId = arguments?.getString("versionId")!!
        brandId = arguments?.getString("brandId")!!
        modeleId = arguments?.getString("modeleId")!!
        totalPrice = parseDouble(versionPrice!!).toInt()
        tv_config_price.text=versionPrice
        versionViewModel = ViewModelProviders.of(this, Injection.provideModelViewModelFactory(modeleId))
            .get(ModelVersionsViewModel::class.java)



        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp)
        toolbar.subtitle = "Version "+versionName
        toolbar.title = getString(R.string.configure_it)

        dialog?.setOnKeyListener { _, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_UP && i == KeyEvent.KEYCODE_BACK) {
                context?.let { exitConfirmation(it) }
            }
            true
        }
        toolbar.setNavigationOnClickListener {
            context?.let { exitConfirmation(it) }
        }

        context?.let {

            var context:Context = it

             colorsList.observe(this.activity!!, Observer<List<PaintColor>>{
                 initColorsChips(context, it)
            })
            optionsList.observe(this.activity!!, Observer<List<Option>>{
                initOptionsChips(context, it)

            })


        }
        button_command_version.isClickable=false
        button_command_version.setTextColor(getResources().getColor(R.color.grey))
        button_command_version.setOnClickListener {
            //TODO : change email when authentification is fixed
            versionViewModel.reserver("fm_bourouais@esi.dz",carId, totalPrice!!.toFloat()).observe(this.activity!!,
                            Observer<String> {
                                if (!(it.equals(""))){
                                    Toast.makeText(this.activity,it,Toast.LENGTH_SHORT).show()
                                }

                            })


        }

    }
    private fun initColorsChips(context: Context, pColors:List<PaintColor>){
        for (lColor in pColors){
            val chip = Chip(context)
            chip.isCheckable = true

            val bitmap: Bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val shapeDrawable = ShapeDrawable(OvalShape() as Shape?)
            shapeDrawable.setBounds( 0, 0, 500, 500)
            shapeDrawable.paint.color = Color.parseColor("#"+lColor.hexCode)
            shapeDrawable.draw(canvas)
            chip.chipIcon = shapeDrawable
            chip.text = lColor.name
            chip_group.addView(chip)
        }
        chip_group.setOnCheckedChangeListener { _, _ ->
            totalPrice = parseDouble(versionPrice!!).toInt().plus(calculateColorPrice() + calculateOptionsPrice())
            //tv_config_price.text = (/*parseDouble(tv_config_price.text.toString()).toInt()+*/calculateColorPrice() + calculateOptionsPrice()).toString()
            tv_config_price.text = totalPrice.toString()
             checkAvailable()

        }


    }
    private fun initOptionsChips(context: Context, pOptions:Collection<Option>){
        for (lOption in pOptions){
            val chip = Chip(context)
            chip.isCheckable = true
            chip.text = lOption.name
            chip_group_options.addView(chip)
            chip.setOnCheckedChangeListener { _, _ ->
                totalPrice = parseDouble(versionPrice!!).toInt().plus(calculateColorPrice() + calculateOptionsPrice())
                tv_config_price.text = totalPrice.toString()
                checkAvailable()

            }
        }
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
    }
    private fun exitConfirmation(context: Context){
        MaterialAlertDialogBuilder(context)
            .setTitle(getString(R.string.exit_confguration))
            .setMessage(getString(R.string.exit_conf_confimation_msg))
            .setPositiveButton(getString(R.string.exit)) { _, _ ->
                dismissAllowingStateLoss()
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }
    private fun calculateColorPrice():Int{
        chosenColor=""
        tv_availability.text = ""
        var lPrice = 0
        val colorSequence = chip_group.children as Sequence<Chip>
        val colorChip: Chip? = colorSequence.filter { view:Chip ->  view.isChecked}.elementAtOrNull(0)
        if (colorChip!= null){
            val color = colorsList.value!!.filter { paintColor -> paintColor.name == colorChip.text }
            lPrice += color.first().price.toInt()
            chosenColor=color.first().code
        }
        return lPrice
    }
    private fun calculateOptionsPrice():Int{
       //chosenColor=""
        tv_availability.text = ""
        chosenOptions.clear()
        var lPrice = 0
        val colorSequence = chip_group_options.children as Sequence<Chip>
        val optionsChiplist = colorSequence.filter { view:Chip ->  view.isChecked}
        optionsChiplist.forEach { chip ->
                val lOption = optionsList.value!!.filter { option -> option.name == chip.text }

                lPrice += lOption.first().price.toInt()
                chosenOptions.add(lOption.first().id)
        }
        return lPrice
    }

    @SuppressLint("ResourceAsColor")
    private  fun checkAvailable()
    {
        versionViewModel.checkAvailable(brandId,modeleId,versionId,chosenColor,chosenOptions,0.0F).observe(this, Observer<String>
        {
            if ( it.length!=0){
                //used get ressources to handle Api lower than 23
                tv_availability.setTextColor(getResources().getColor(R.color.green));
                tv_availability.text = "Disponible"
                button_command_version.isClickable=true
                button_command_version.setTextColor(getResources().getColor(R.color.colorPrimary))
                carId=it



       }
        else{
                tv_availability.setTextColor(getResources().getColor(R.color.red));
                tv_availability.text = "Non Disponible"
                button_command_version.isClickable=false
                button_command_version.setTextColor(getResources().getColor(R.color.grey))
        }

    })


}}
