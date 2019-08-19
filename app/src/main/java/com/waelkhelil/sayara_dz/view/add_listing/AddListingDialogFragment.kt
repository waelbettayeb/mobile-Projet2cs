package com.waelkhelil.sayara_dz.view.add_listing

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.waelkhelil.sayara_dz.R
import dev.sasikanth.colorsheet.ColorSheet
import dev.sasikanth.colorsheet.utils.ColorSheetUtils
import kotlinx.android.synthetic.main.fragment_add_listing.*
import kotlinx.android.synthetic.main.fragment_add_listing.view.*
import kotlinx.android.synthetic.main.fragment_add_listing.view.button_color


class AddListingDialogFragment : DialogFragment() {

    companion object {
        const val TAG: String = "AddListingDialogFragment"
        fun newInstance() = AddListingDialogFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp)
        toolbar.title = getString(R.string.new_listing)

        dialog?.setOnKeyListener { _, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_UP && i == KeyEvent.KEYCODE_BACK) {
                context?.let { exitConfirmation(it) }
            }
            true
        }
        toolbar.setNavigationOnClickListener {
            context?.let { exitConfirmation(it) }
        }
        // Options view
        view.etValue.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val txtVal = v.text
                if(!txtVal.isNullOrEmpty()) {
                    addChipToGroup(txtVal.toString(), view.chipGroup2)
                    view.etValue.setText("")
                }

                return@OnEditorActionListener true
            }
            false
        })

        val colors = resources.getIntArray(R.array.colors)
        context?.let { it1 -> setColor(it1, colors.first()) }
        button_color.setOnClickListener {
            fragmentManager?.let {
                ColorSheet().colorPicker(
                    colors = colors,
                    selectedColor = colors.first(),
                    listener = { color ->
                        context?.let { it1 -> setColor(it1, color) }
                    })
                    .show(it)
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
            .setTitle(getString(R.string.exit_add_listing))
            .setMessage(getString(R.string.exit_add_listing_confimation_msg))
            .setPositiveButton(getString(R.string.exit)) { _, _ ->
                dismissAllowingStateLoss()
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }
    private fun addChipToGroup(txt: String, chipGroup: ChipGroup) {
        val chip = Chip(context)
        chip.text = txt
//        chip.chipIcon = ContextCompat.getDrawable(requireContext(), baseline_person_black_18)

        // necessary to get single selection working
        chip.isClickable = false
        chip.isCloseIconVisible = true
        chip.isCheckable = false
        chipGroup.addView(chip as View)
        chip.setOnCloseIconClickListener { chipGroup.removeView(chip as View) }
        printChipsValue(chipGroup)
    }

    private fun printChipsValue(chipGroup: ChipGroup) {
        for (i in 0 until chipGroup.childCount) {
            val chipObj = chipGroup.getChildAt(i) as Chip
            Log.d("Chips text :: " , chipObj.text.toString())

        }
    }
    private fun setColor(context: Context, @ColorInt color: Int) {
        if (color != ColorSheet.NO_COLOR) {
            button_color.setBackgroundColor(color)
            button_color.text = ColorSheetUtils.colorToHex(color)
        } else {
            val primaryColor = ContextCompat.getColor(context, R.color.colorPrimary)
            button_color.setBackgroundColor(primaryColor)
            button_color.text = getString(R.string.no_color)
        }
    }
}
