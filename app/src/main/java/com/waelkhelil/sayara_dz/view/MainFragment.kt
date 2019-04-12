package com.waelkhelil.sayara_dz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.waelkhelil.sayara_dz.R
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lBottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        navController = findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(lBottomNavigationView, navController)
    }
}
