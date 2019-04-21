package com.waelkhelil.sayara_dz.view.brand_ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.model.Model
import kotlinx.android.synthetic.main.fragment_brand.*

class BrandFragment : Fragment() {

    companion object {
        fun newInstance() = BrandFragment()
    }

    private lateinit var viewModel: BrandViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_brand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)
        toolbar.title = "Renault"

        Glide
            .with(view)
            .load("https://logo.clearbit.com/renault.com")
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.icon_mono)
            .into(view.findViewById(R.id.image_brand_logo))


        val list:List<Model> = listOf(
            Model(0, "Clio", "https://www.renault.fr/content/dam/Renault/master/vehicules/clio-bja/reveal/renault-clio-reveal-022.jpg"),
            Model(1, "Mégane", ""),
            Model(2, "Kadjar", "https://www.cdn.renault.com/content/dam/Renault/FR/personal-cars/Kadjar/hfe-kadjar/hfe-phase2/plan-produit/Campagne%20janvier%2019/renault_nouveau_kadjar_5.jpg.ximg.l_8_m.smart.jpg"),
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BrandViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
