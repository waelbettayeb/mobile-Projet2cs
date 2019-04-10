package com.waelkhelil.sayara_dz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.waelkhelil.sayara_dz.R


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var navController : NavController
        private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

            R.id.navigation_home -> {
                navController.navigate(R.id.home_fragment)
            }
            R.id.navigation_search -> {
                navController.navigate(R.id.search_fragment)

            }
            R.id.navigation_notification -> {
                navController.navigate(R.id.notification_fragment)

            }
            R.id.navigation_user -> {
                navController.navigate(R.id.fragment_user_content)
            }
        }
        true
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentContainer = view.findViewById<View>(R.id.nav_main_host_fragment)
        navController = findNavController(fragmentContainer)

        val lBottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        lBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
