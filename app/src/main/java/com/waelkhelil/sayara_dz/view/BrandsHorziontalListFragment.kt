package com.waelkhelil.sayara_dz.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.Brand
import kotlinx.android.synthetic.main.horziontal_list_fragment.*


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
            parentActivity.setFragment(BrandsListFragment(), "brands_list", "home")
        }

        val parentActivity:MainActivity = activity as MainActivity
        val list:List<Brand> = listOf(
            Brand("id01","Toyota" ),
            Brand("id01","Audi" ),
            Brand("id01","BMW" ),
            Brand("id01","Renault" ),
            Brand("id01","Mini" )
        )
        rv_horizontal_list?. adapter = CardsListItemAdapter(list)
        var lLayoutManager = LinearLayoutManager(activity)
        lLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rv_horizontal_list.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = lLayoutManager
            // set the custom adapter to the RecyclerView
            adapter = CardsListItemAdapter(list)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelBrands = ViewModelProviders.of(this).get(BrandsHorziontalListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
