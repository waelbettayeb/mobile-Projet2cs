package com.waelkhelil.sayara_dz.view.model_ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.waelkhelil.sayara_dz.R


class ModelFragment : Fragment() {

    companion object {
        fun newInstance() = ModelFragment()
    }

    private lateinit var viewModel: ModelViewModel
    private lateinit var mMenu: Menu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_model, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager : ViewPager = view.findViewById(R.id.view_pager_model)
        val tabs:TabLayout = view.findViewById(R.id.tabs_model)
        tabs.setupWithViewPager(viewPager)
        viewPager.adapter = fragmentManager?.let { ModelPagerAdapter(it) }

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)

        toolbar.inflateMenu(R.menu.model_menu)
        mMenu = toolbar.menu

        toolbar.setOnMenuItemClickListener { item: MenuItem ->  this.onOptionsItemSelected(item)}

        toolbar.subtitle = "Renault" // set the subtitle first
        toolbar.title = "Clio"

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_favorite) {
            if(viewModel.isFavorite.value!!){
                Toast.makeText(context, "unsubscribed", Toast.LENGTH_SHORT).show()
                viewModel.setFavorite(false)
            } else{
                Toast.makeText(context, "subscribed", Toast.LENGTH_SHORT).show()
                viewModel.setFavorite(true)
            }
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ModelViewModel::class.java)
        viewModel.isFavorite.observe(this, Observer<Boolean> {
            if(it)
                mMenu.getItem(0).setIcon(R.drawable.ic_notifications_black_24dp)
            else
                mMenu.getItem(0).setIcon(R.drawable.ic_menu_notifications_none_black_24dp)

        })
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

}
