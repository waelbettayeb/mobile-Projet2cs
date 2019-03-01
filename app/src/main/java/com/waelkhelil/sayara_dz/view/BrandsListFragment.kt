package com.waelkhelil.sayara_dz.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.waelkhelil.sayara_dz.Brand
import com.waelkhelil.sayara_dz.R
import kotlinx.android.synthetic.main.brands_list_fragment.*


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         val test = listOf(
            Brand("id","Renault"),
            Brand("id","Peugeot"),
            Brand("id","Volkswaegn")
        )
        val parentActivity:MainActivity = activity as MainActivity
        rv_brands_list.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(parentActivity)
            // set the custom adapter to the RecyclerView
            adapter = BrandsListAdapter(test)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BrandsListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
