package com.waelkhelil.sayara_dz.view.brands_vertical_list_ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.Injection
import com.waelkhelil.sayara_dz.database.model.Brand
import com.waelkhelil.sayara_dz.view.home_ui.BrandListViewModel
import com.waelkhelil.sayara_dz.view.home_ui.HorizontalListFragment
import kotlinx.android.synthetic.main.fragment_brands_list.*
import kotlinx.android.synthetic.main.fragment_brands_list.view.*

class BrandsListFragment(): Fragment(){
companion object {
    fun newInstance() = HorizontalListFragment()
}

private lateinit var viewModel: BrandListViewModel
private lateinit var adapter : BrandsListAdapter


override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_brands_list, container, false)

}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    //get the view model
    viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(this.activity!!))
        .get(BrandListViewModel::class.java)
    initAdapter()

    val toolbar: Toolbar = view.findViewById(R.id.toolbar)
    val navController = Navigation.findNavController(requireActivity(), R.id.nav_main_host_fragment)
    NavigationUI.setupWithNavController(toolbar, navController)





}

override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    //viewModel = ViewModelProviders.of(this).get(BrandListViewModel::class.java)
}

@SuppressLint("WrongConstant")
private fun initAdapter() {
    viewModel.init()
    viewModel.getNewsRepository()!!.observe(this.activity!!, Observer<List<Brand>>  {
        if (it!=null){




            val lLayoutManager = LinearLayoutManager(activity)
            lLayoutManager.orientation = LinearLayoutManager.VERTICAL
            adapter= BrandsListAdapter(it)
            if (rv_brands_list!=null){
                rv_brands_list.adapter=adapter
                rv_brands_list.apply {
                    // set a LinearLayoutManager to handle Android
                    // RecyclerView behavior
                    layoutManager = lLayoutManager
                    val dividerItemDecoration = DividerItemDecoration(
                        rv_brands_list.context,
                        LinearLayoutManager.VERTICAL
                    )
                    // set the custom adapter to the RecyclerView

                }}}



        viewModel.getNetworkErrors()!!.observe(this.activity!!, Observer<String> {
            Log.i("errorrr","here")
            Toast.makeText(this.activity, R.string.brands_error, Toast.LENGTH_SHORT).show()
        })
    });
}}








