package com.waelkhelil.sayara_dz.view.configure_version

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.waelkhelil.sayara_dz.R


class ConfigureVersionFragment : Fragment() {

    companion object {
        fun newInstance() = ConfigureVersionFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_configure_version, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val versionId = arguments?.getInt("versionId")

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)
        toolbar.subtitle = versionId.toString() // set the subtitle first
        toolbar.title = "Configurez votre voiture"
    }
}
