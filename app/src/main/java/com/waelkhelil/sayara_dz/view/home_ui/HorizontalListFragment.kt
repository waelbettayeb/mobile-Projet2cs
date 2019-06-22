package com.waelkhelil.sayara_dz.view.home_ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.Injection
import com.waelkhelil.sayara_dz.database.model.Brand
import kotlinx.android.synthetic.main.horziontal_list_fragment.*




class HorizontalListFragment : Fragment() {

    companion object {
        fun newInstance() = HorizontalListFragment()
    }

    private lateinit var viewModel: BrandListViewModel
    private  lateinit var adapter: CardsListItemAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.waelkhelil.sayara_dz.R.layout.horziontal_list_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get the view model
        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(this.activity!!))
            .get(BrandListViewModel::class.java)


        val lBundle: Bundle? = arguments
        if (lBundle != null) {
            text_list_name.setText(lBundle.getString("header"))
            if(lBundle.getBoolean("see_all_button_hidden"))
                button_see_all_brands.visibility = TextView.INVISIBLE
        }
        val lButtonSeeAll = getView()!!.
                findViewById<Button>(com.waelkhelil.sayara_dz.R.id.button_see_all_brands)
        lButtonSeeAll.setOnClickListener {
            val fragmentContainer = activity?.findViewById<View>(com.waelkhelil.sayara_dz.R.id.nav_main_host_fragment)
            val navController = fragmentContainer?.let { Navigation.findNavController(it)}
            navController?.navigate(com.waelkhelil.sayara_dz.R.id.action_global_to_brandsListFragment)
        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        //viewModel = ViewModelProviders.of(this).get(BrandListViewModel::class.java)
    }

    private fun initAdapter() {

        viewModel.init()
        viewModel.getNewsRepository()!!.observe(this.activity!!, Observer<List<Brand>>  {
             if (it!=null){




            val lLayoutManager = LinearLayoutManager(activity)
            lLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            adapter=CardsListItemAdapter(it)
               if (rv_horizontal_list!=null){
            rv_horizontal_list.adapter=adapter
            rv_horizontal_list.apply {
                // set a LinearLayoutManager to handle Android
                // RecyclerView behavior
                layoutManager = lLayoutManager
                // set the custom adapter to the RecyclerView

            }}}



        viewModel.getNetworkErrors()!!.observe(this.activity!!, Observer<String> {
            Log.i("errorrr","here")
            Toast.makeText(this.activity, R.string.brands_error, Toast.LENGTH_SHORT).show()
        })
       });
    }

}
