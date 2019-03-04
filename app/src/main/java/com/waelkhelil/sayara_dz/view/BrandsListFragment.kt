package com.waelkhelil.sayara_dz.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.*
import com.waelkhelil.sayara_dz.database.Brand
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.AppDatabase
import com.waelkhelil.sayara_dz.database.BrandDao
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

        val parentActivity:MainActivity = activity as MainActivity
        val list:List<Brand> = listOf<Brand>()
        rv_brands_list?. adapter = BrandsListAdapter(list)
        rv_brands_list.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = BrandsListAdapter(list)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BrandsListViewModel::class.java)

        Log.d("BrandsListFragment", "onActivityCreated")
        viewModel.getBrands().observe(this, Observer<List<Brand>>{ brands ->
//            Log.i("BrandsListFragment", brands.toString())
//
//            val adapter = BrandsListAdapter(brands)
//            rv_brands_list.adapter = adapter
//            adapter.notifyDataSetChanged()
        })
//        val mBrandDao: BrandDao = AppDatabase.getDatabase(activity!!.application).brandDao()
//        MainActivity.doAsync {
////            mBrandDao.insertAll(
////                Brand("id4", "BMW"),
////                Brand("id5", "Audi")
////            )
//            var b = mBrandDao.getAlll()
//            Log.i("BrandsListFragment", b.toString())
//
//            var bl = mBrandDao.getAll()
//            Log.i("BrandsListFragment", bl.toString())
//        }.execute()
    }

}
