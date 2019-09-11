package com.waelkhelil.sayara_dz.view.home_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.model.Brand
import com.waelkhelil.sayara_dz.database.model.Listing
import com.waelkhelil.sayara_dz.view.add_listing.ImageAdapter
import kotlinx.android.synthetic.main.fragment_explore.*


class ExploreFragment : Fragment() {

    companion object {
        fun newInstance() = ExploreFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf(Listing("zec,", "zch", "jze "),
            Listing("zec", "zch", "jze "))
        rv_listing_list.apply {
            adapter = ListingListItemAdapter(list)
        }
    }
}
