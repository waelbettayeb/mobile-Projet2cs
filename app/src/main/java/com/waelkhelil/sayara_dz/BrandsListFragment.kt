package com.waelkhelil.sayara_dz

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class BrandsListFragment : Fragment() {

    companion object {
        fun newInstance() = BrandsListFragment()
    }

    private lateinit var viewModel: BrandsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.brands_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BrandsListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
