package com.waelkhelil.sayara_dz.view.home_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.Brand
import com.waelkhelil.sayara_dz.view.brands_ui.BrandsListFragment
import com.waelkhelil.sayara_dz.view.MainActivity
import kotlinx.android.synthetic.main.horziontal_list_fragment.*


class HorziontalListFragment() : Fragment() {

    companion object {
        fun newInstance() = HorziontalListFragment()
    }

    private lateinit var viewModel: HorziontalListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.horziontal_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val lBundle: Bundle? = arguments
        if (lBundle != null) {
            text_list_name.setText(lBundle.getString("header"))
            if(lBundle.getBoolean("see_all_button_hidden"))
                button_see_all_brands.visibility = TextView.INVISIBLE
        }
        val lButtonSeeAll = getView()!!.
                findViewById<Button>(R.id.button_see_all_brands)
        lButtonSeeAll.setOnClickListener {
            val parentActivity: MainActivity = activity as MainActivity
            parentActivity.setFragment(BrandsListFragment(), "brands_list", "home")
        }

        val parentActivity: MainActivity = activity as MainActivity
        val list:List<Brand> = listOf(
            Brand(0,"Toyota" ,R.mipmap.ic_launcher),
            Brand(1,"Audi" ,R.mipmap.ic_launcher),
            Brand(2,"BMW" ,R.mipmap.ic_launcher),
            Brand(3,"Renault",R.mipmap.ic_launcher ),
            Brand(4,"Mini",R.mipmap.ic_launcher)
        )
//        rv_horizontal_list?. adapter = CardsListItemAdapter(list)
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
        viewModel = ViewModelProviders.of(this).get(HorziontalListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
