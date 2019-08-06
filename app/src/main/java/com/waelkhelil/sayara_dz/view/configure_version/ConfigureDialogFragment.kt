package com.waelkhelil.sayara_dz.view.configure_version

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.model.Option
import com.waelkhelil.sayara_dz.database.model.PaintColor
import kotlinx.android.synthetic.main.fragment_configure_version.*


class ConfigureDialogFragment : DialogFragment() {

    companion object {
        const val TAG: String = "ConfigureDialogFragment"
        fun newInstance() = ConfigureDialogFragment()
    }
    val colorsList:List<PaintColor> = listOf(
        PaintColor("red", "#2196F3",0),
        PaintColor("blue", "#FF6050",100),
        PaintColor("green", "#FF0E83",200),
        PaintColor("yellow", "#839BFD",200),
        PaintColor("white", "#DDE3FE",400)
    )
    val optionsList = setOf(Option(0, "option_00",0),Option(1, "option_01",1),Option(2, "option_02",2),
        Option(3, "option_03",3))
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
        val versionId = arguments?.getInt("versionId")
        val versionName = "version " + versionId.toString()
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp)
        toolbar.subtitle = versionName // set the subtitle first
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
            initColorsChips(it, colorsList)
            initOptionsChips(it, optionsList)
        }

    }
    private fun initColorsChips(context: Context, pColors:List<PaintColor>){
        for (lColor in pColors){
            val chip = Chip(context)
            chip.isCheckable = true

            val bitmap: Bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val shapeDrawable = ShapeDrawable(OvalShape())
            shapeDrawable.setBounds( 0, 0, 500, 500)
            shapeDrawable.paint.color = Color.parseColor(lColor.hexCode)
            shapeDrawable.draw(canvas)
            chip.chipIcon = shapeDrawable
            chip.text = lColor.name
            chip_group.addView(chip)
        }
        chip_group.setOnCheckedChangeListener { _, _ ->
            tv_config_price.text = (calculateColorPrice() + calculateOptionsPrice()).toString()
        }
    }
    private fun initOptionsChips(context: Context, pOptions:Collection<Option>){
        for (lOption in pOptions){
            val chip = Chip(context)
            chip.isCheckable = true
            chip.text = lOption.name
            chip_group_options.addView(chip)
            chip.setOnCheckedChangeListener { _, _ ->
                tv_config_price.text = (calculateColorPrice() + calculateOptionsPrice()).toString()
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
        var lPrice = 0
        val colorSequence = chip_group.children as Sequence<Chip>
        val colorChip: Chip? = colorSequence.filter { view:Chip ->  view.isChecked}.elementAtOrNull(0)
        if (colorChip!= null){
            val color = colorsList.filter { paintColor -> paintColor.name == colorChip.text }
            lPrice += color.first().price
        }
        return lPrice
    }
    private fun calculateOptionsPrice():Int{
        var lPrice = 0
        val colorSequence = chip_group_options.children as Sequence<Chip>
        val optionsChiplist = colorSequence.filter { view:Chip ->  view.isChecked}
        optionsChiplist.forEach { chip ->
                val lOption = optionsList.filter { option -> option.name == chip.text }
                lPrice += lOption.first().price
        }
        return lPrice
    }
}
