package com.waelkhelil.sayara_dz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.waelkhelil.sayara_dz.R
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class NotificationFragment : Fragment() {

    companion object {
        fun newInstance() = NotificationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager : ViewPager = view.findViewById(R.id.view_pager_notification)
        val tabs = view.findViewById(R.id.tabs) as TabLayout
        viewPager.adapter = fragmentManager?.let { NotificationPagerAdapter(it) }
        tabs.setupWithViewPager(viewPager)
    }

    inner class NotificationPagerAdapter(pFragmentManager:FragmentManager) : FragmentStatePagerAdapter(pFragmentManager) {


        private val mFragmentList = mutableListOf(NotificationSubscriptionsFragment(),
            NotificationBidsFragment())
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
