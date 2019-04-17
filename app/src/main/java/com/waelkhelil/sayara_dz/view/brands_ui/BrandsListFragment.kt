package com.waelkhelil.sayara_dz.view.brands_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.model.Brand
import kotlinx.android.synthetic.main.fragment_brands_list.*


class BrandsListFragment : Fragment() {

    companion object {
        fun newInstance() = BrandsListFragment()
    }

    private lateinit var viewModel: BrandsListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_brands_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val list:List<Brand> = listOf(
            Brand(0, "Toyota", ""),
            Brand(1, "Audi", ""),
            Brand(1, "Audi", ""),
            Brand(2, "BMW", ""),
            Brand(3, "Renault", ""),
            Brand(4, "Renault", ""),
            Brand(5, "Renault", ""),
            Brand(6, "Mini", "") ,
            Brand(2, "BMW", ""),
            Brand(3, "Renault", ""),
            Brand(4, "Renault", ""),
            Brand(5, "Renault", ""),
            Brand(6, "Mini", ""),
            Brand(6, "Mini", "") ,
            Brand(2, "BMW", ""),
            Brand(3, "Renault", ""),
            Brand(4, "Renault", ""),
            Brand(5, "Renault", ""),
            Brand(6, "Mini", "")
        )
        val recyclerView = rv_brands_list
        recyclerView.adapter = BrandsListAdapter(list)

        recyclerView.layoutManager = LinearLayoutManager(activity)

    }

}
