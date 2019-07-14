package com.waelkhelil.sayara_dz.view.configure_version

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.waelkhelil.sayara_dz.R

class ConfigureDialogFragment : DialogFragment() {

    companion object {
        const val TAG: String = "ConfigureDialogFragment"
        fun newInstance() = ConfigureDialogFragment()
    }

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

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp)
        toolbar.subtitle = versionId.toString() // set the subtitle first
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
}
