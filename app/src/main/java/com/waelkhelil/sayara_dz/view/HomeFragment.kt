package com.waelkhelil.sayara_dz.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.waelkhelil.sayara_dz.R


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }


    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val lHomeTopFragment= HomeTopFragment()
        fragmentTransaction.add(R.id.lists_fragment_container, lHomeTopFragment)

        val lBrandsHorziontalListFragment = HorziontalListFragment()
        fragmentTransaction.add(R.id.lists_fragment_container, lBrandsHorziontalListFragment)

//      preparing the Models list
        val lModelsHorziontalListFragment = HorziontalListFragment()
        val lBundle:Bundle = Bundle()

        lBundle.putString("header", "Models")
        lBundle.putBoolean("see_all_button_hidden", true)

        lModelsHorziontalListFragment.arguments = lBundle

        fragmentTransaction.add(R.id.lists_fragment_container, lModelsHorziontalListFragment)

        val lHomeSignInFragment = HomeSignInFragment()
        fragmentTransaction.add(R.id.lists_fragment_container, lHomeSignInFragment)
            .commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
