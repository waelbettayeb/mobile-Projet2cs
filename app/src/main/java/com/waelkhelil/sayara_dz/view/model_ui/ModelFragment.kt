package com.waelkhelil.sayara_dz.view.model_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.waelkhelil.sayara_dz.R

class ModelFragment : Fragment() {

    companion object {
        fun newInstance() = ModelFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_model, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager : ViewPager = view.findViewById(R.id.view_pager_model)
        val tabs = view.findViewById(R.id.tabs_model) as TabLayout
        tabs.setupWithViewPager(viewPager)
        viewPager.adapter = fragmentManager?.let { ModelPagerAdapter(it) }

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)
        toolbar.subtitle = "Renault" // set the subtitle first
        toolbar.title = "Clio"

    }

    inner class ModelPagerAdapter(pFragmentManager: FragmentManager) : FragmentStatePagerAdapter(pFragmentManager) {

        private val mFragmentList = mutableListOf(
            ModelInfoFragment(),
            ModelVersionsFragment()
        )
        private val mFragmentTitleList = mutableListOf(resources.getString(R.string.information),
            resources.getString(R.string.versions))

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        R.id.action_favorite -> {
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

}
