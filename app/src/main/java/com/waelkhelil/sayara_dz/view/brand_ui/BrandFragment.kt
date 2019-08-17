package com.waelkhelil.sayara_dz.view.brand_ui

import android.annotation.SuppressLint
import android.os.Bundle
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.Injection
import com.waelkhelil.sayara_dz.database.model.Model
import kotlinx.android.synthetic.main.fragment_brand.*

class BrandFragment : Fragment() {

    companion object {
        fun newInstance() = BrandFragment()
    }

    private lateinit var viewModel: ModelViewModel
    private  lateinit var adapter: ModelsListItemAdapter
    private  lateinit var brand_id: String
    private  lateinit var brand_logo: String
    private  lateinit var brand_name: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_brand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        brand_name = arguments!!.getString("brand_name")
        brand_id = arguments!!.getString("brand_id")
        brand_logo = arguments!!.getString("brand_logo")
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)

        toolbar.title = brand_name
        tv_brand_name.text = brand_name
        Glide
            .with(view)
            .load(brand_logo)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.icon_mono)
            .into(view.findViewById(R.id.image_brand_logo))

    }



        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            //get the view model
            viewModel = ViewModelProviders.of(this, Injection.provideModelViewModelFactory(brand_id))
                .get(ModelViewModel::class.java)
            initAdapter()
        }

        @SuppressLint("WrongConstant")
        private fun initAdapter() {

              var list :ArrayList<Model> = ArrayList<Model>()
            viewModel.init()
            viewModel.getRepository()!!.observe(this.activity!!, Observer<List<Model>>  {



                tv_models_nb.text=it.size.toString()
                    val lLayoutManager = LinearLayoutManager(activity)
                    lLayoutManager.orientation = LinearLayoutManager.VERTICAL

                    adapter= ModelsListItemAdapter(it,brand_name,this,viewModel)
                    if (rv_models_list!=null){
                        rv_models_list.adapter=adapter
                        rv_models_list.apply {
                            // set a LinearLayoutManager to handle Android
                            // RecyclerView behavior
                            layoutManager = lLayoutManager
                            // set the custom adapter to the RecyclerView

                        }}



                viewModel.getNetworkErrors()!!.observe(this.activity!!, Observer<String> {
                    Toast.makeText(this.activity, R.string.brands_error, Toast.LENGTH_SHORT).show()
                })
            });
        }

        }



