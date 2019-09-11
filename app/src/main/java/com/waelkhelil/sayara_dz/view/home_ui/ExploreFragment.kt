package com.waelkhelil.sayara_dz.view.home_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.model.Listing
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
        val list = listOf<Listing>(Listing("", "", ""))
        rv_listing_list.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            // set the custom adapter to the RecyclerView
            adapter = ListingListItemAdapter(list)
        }
    }
}
