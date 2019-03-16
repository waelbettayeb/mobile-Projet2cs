package com.waelkhelil.sayara_dz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.waelkhelil.sayara_dz.R
import kotlinx.android.synthetic.main.brands_list_fragment.*


class BrandsListFragment() : Fragment() {

    companion object {
        fun newInstance() = BrandsListFragment()
    }

    private lateinit var viewModel: BrandsListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.brands_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recyclerView = rv_brands_list
        val adapter = BrandListAdapter(activity!!.baseContext)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity!!.baseContext)
         lateinit var viewModel: BrandsListViewModel
         viewModel = ViewModelProviders.of(this).get(BrandsListViewModel::class.java)
         viewModel.Allbrands.observe(this, Observer { brands ->
            // Update the cached copy of the words in the adapter.
            brands?.let { adapter.setBrands(it) }
        })

    }

}
