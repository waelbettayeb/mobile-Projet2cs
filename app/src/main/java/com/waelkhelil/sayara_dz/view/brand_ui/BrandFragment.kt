package com.waelkhelil.sayara_dz.view.brand_ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
    private  lateinit var pBrandId: String
    private  lateinit var pBrandLogoUrl: String
    private  lateinit var pBrandName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_brand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
<<<<<<< HEAD
         pBrandName = arguments!!.getString("brand_name").toString()
         pBrandId = arguments!!.getString("brand_id").toString()
         pBrandLogoUrl = arguments!!.getString("brand_logo").toString()
=======
        brand_name = arguments!!.getString("brand_name")
        brand_id = arguments!!.getString("brand_id")
        brand_logo = arguments!!.getString("brand_logo")
>>>>>>> f6ea8887d08420c0b77e50d02bbb2ec7b586d325
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)

        toolbar.title = pBrandName
        tv_brand_name.text = pBrandName
        Glide
            .with(view)
            .load(pBrandLogoUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.icon_mono)
            .into(view.findViewById(R.id.image_brand_logo))

    }



        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            //get the view model
<<<<<<< HEAD
            viewModel = ViewModelProviders.of(this, Injection.provideModelViewModelFactory(this.activity!!,pBrandId))
=======
            viewModel = ViewModelProviders.of(this, Injection.provideModelViewModelFactory(brand_id))
>>>>>>> f6ea8887d08420c0b77e50d02bbb2ec7b586d325
                .get(ModelViewModel::class.java)
            initAdapter()
        }

        @SuppressLint("WrongConstant")
        private fun initAdapter() {


            viewModel.init()
            viewModel.getRepository()!!.observe(this.activity!!, Observer<List<Model>>  {
                tv_models_nb.text=it.size.toString()
<<<<<<< HEAD
                val lLayoutManager = LinearLayoutManager(activity)
                lLayoutManager.orientation = LinearLayoutManager.VERTICAL
                adapter = ModelsListItemAdapter(it,pBrandName)
                rv_models_list.adapter = adapter
                rv_models_list.apply {
                    // set a LinearLayoutManager to handle Android
                    // RecyclerView behavior
                    layoutManager = lLayoutManager
                    // set the custom adapter to the RecyclerView

                }

            })

            viewModel.networkErrors.observe(this, Observer<String> {
                Toast.makeText(this.activity, R.string.models_error, Toast.LENGTH_SHORT).show()
            })
=======
                    val lLayoutManager = LinearLayoutManager(activity)
                    lLayoutManager.orientation = LinearLayoutManager.VERTICAL
                    adapter= ModelsListItemAdapter(it,brand_name)
                    if (rv_models_list!=null){
                        rv_models_list.adapter=adapter
                        rv_models_list.apply {
                            // set a LinearLayoutManager to handle Android
                            // RecyclerView behavior
                            layoutManager = lLayoutManager
                            // set the custom adapter to the RecyclerView

                        }}



                viewModel.getNetworkErrors()!!.observe(this.activity!!, Observer<String> {
                    Log.i("errorrr","here")
                    Toast.makeText(this.activity, R.string.brands_error, Toast.LENGTH_SHORT).show()
                })
            });
>>>>>>> f6ea8887d08420c0b77e50d02bbb2ec7b586d325
        }

        }



/* val list:List<Model> = listOf(
     Model(
         0,
         "Clio",
         "https://www.renault.fr/content/dam/Renault/master/vehicules/clio-bja/reveal/renault-clio-reveal-022.jpg"
     ),
     Model(1, "Mégane", ""),
     Model(
         2,
         "Kadjar",
         "https://www.cdn.renault.com/content/dam/Renault/FR/personal-cars/Kadjar/hfe-kadjar/hfe-phase2/plan-produit/Campagne%20janvier%2019/renault_nouveau_kadjar_5.jpg.ximg.l_8_m.smart.jpg"
     ),
     Model(3, "Renault", ""),
     Model(4, "Renault", ""),
     Model(5, "Renault", ""),
     Model(6, "Mini", "")
 )
//        rv_horizontal_list?. adapter = CardsListItemAdapter(list)
 val lLayoutManager = LinearLayoutManager(activity)
 rv_models_list.apply {
     // set a LinearLayoutManager to handle Android
     // RecyclerView behavior
     layoutManager = lLayoutManager
     // set the custom adapter to the RecyclerView
     adapter = ModelsListItemAdapter(list)
 }
}*/
