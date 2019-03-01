package com.waelkhelil.sayara_dz.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.waelkhelil.sayara_dz.R


class BrandsHorziontalListFragment : Fragment() {

    companion object {
        fun newInstance() = BrandsHorziontalListFragment()
    }

    private lateinit var viewModelBrands: BrandsHorziontalListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.horziontal_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val lButtonSeeAll = getView()!!.
                findViewById<Button>(R.id.see_all_brands_button)
        lButtonSeeAll.setOnClickListener {
            val parentActivity:MainActivity = activity as MainActivity
            parentActivity.setFragment(BrandsListFragment())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelBrands = ViewModelProviders.of(this).get(BrandsHorziontalListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
