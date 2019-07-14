package com.waelkhelil.sayara_dz.view.configure_version

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.waelkhelil.sayara_dz.R


class ConfigureVersionFragment : Fragment() {

    companion object {
        fun newInstance() = ConfigureVersionFragment()
    }
    private lateinit var navController: NavController
    private lateinit var callback: OnBackPressedCallback
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_configure_version, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val versionId = arguments?.getInt("versionId")

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)
        toolbar.subtitle = versionId.toString() // set the subtitle first
        toolbar.title = getString(R.string.configure_it)
//        callback = object : OnBackPressedCallback(true /** true means that the callback is enabled */) {
//            override fun handleOnBackPressed() {
//                context?.let { exitConfirmation(it) }
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
//        toolbar.setNavigationOnClickListener {
//            context?.let { exitConfirmation(it) }
//        }
    }
    fun exitConfirmation(context:Context){
        MaterialAlertDialogBuilder(context)
                .setTitle(getString(R.string.exit_confguration))
                .setMessage(getString(R.string.exit_conf_confimation_msg))
                .setPositiveButton(getString(R.string.exit)) { _, _ ->
                    callback.isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
                .setNegativeButton(getString(R.string.cancel), null)
                .show()
    }

}
