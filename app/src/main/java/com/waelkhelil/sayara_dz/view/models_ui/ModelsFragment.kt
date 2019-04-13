package com.waelkhelil.sayara_dz.view.models_ui

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
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.model.Model
import kotlinx.android.synthetic.main.models_fragment.*

class ModelsFragment : Fragment() {

    companion object {
        fun newInstance() = ModelsFragment()
    }

    private lateinit var viewModel: ModelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.models_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)
        toolbar.title = "Renault"

        val lBundle: Bundle? = arguments
        if (lBundle != null) {

        }
        val list:List<Model> = listOf(
            Model(0, "Toyota", ""),
            Model(1, "Audi", ""),
            Model(2, "BMW", ""),
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
            adapter = ModelListItemAdapter(list)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ModelViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
