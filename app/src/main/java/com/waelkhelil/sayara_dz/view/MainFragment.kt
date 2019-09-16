package com.waelkhelil.sayara_dz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.SharedViewModel
import com.waelkhelil.sayara_dz.database.model.Version


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var navController: NavController
    private lateinit var sharedViewModel: SharedViewModel

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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lBottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        navController = findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(lBottomNavigationView, navController)

        lBottomNavigationView.getOrCreateBadge(R.id.notification_fragment)
        sharedViewModel.mCompareList.observe(viewLifecycleOwner, Observer<Set<Version>> {
            if (it.size > 1) {
                lBottomNavigationView.getOrCreateBadge(R.id.home_fragment)
            } else {
                lBottomNavigationView.removeBadge(R.id.home_fragment)
            }
        })

    }




}