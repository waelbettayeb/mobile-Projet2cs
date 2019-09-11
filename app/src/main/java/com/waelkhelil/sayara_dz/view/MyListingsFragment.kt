package com.waelkhelil.sayara_dz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.model.Listing
import com.waelkhelil.sayara_dz.view.home_ui.ListingListItemAdapter
import kotlinx.android.synthetic.main.fragment_explore.*


class MyListingsFragment : Fragment() {

    companion object {
        fun newInstance() = MyListingsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_listings, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf(
            Listing("zec,", "zch", "jze "),
            Listing("zec", "zch", "jze ")
        )
        rv_listing_list.apply {
            adapter = ListingListItemAdapter(list)
        }
    }
}
