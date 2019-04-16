package com.waelkhelil.sayara_dz.view.model_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.view.NotificationBidsFragment
import com.waelkhelil.sayara_dz.view.NotificationSubscriptionsFragment

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

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)
        toolbar.title = "Clio"
        Glide
            .with(view)
            .load("https://www.renault.fr/content/dam/Renault/master/vehicules/clio-bja/reveal/renault-clio-reveal-022.jpg")
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.icon_mono)
            .into(view.findViewById(R.id.image_model))
        val viewPager : ViewPager = view.findViewById(R.id.view_pager_model)
        val tabs = view.findViewById(R.id.tabs_model) as TabLayout
        viewPager.adapter = fragmentManager?.let { ModelPagerAdapter(it) }
        tabs.setupWithViewPager(viewPager)
    }

    inner class ModelPagerAdapter(pFragmentManager: FragmentManager) : FragmentStatePagerAdapter(pFragmentManager) {

        private val mFragmentList = mutableListOf(
            NotificationSubscriptionsFragment(),
            NotificationBidsFragment()
        )
        private val mFragmentTitleList = mutableListOf(resources.getString(R.string.subscriptions),
            resources.getString(R.string.bids))

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

}
