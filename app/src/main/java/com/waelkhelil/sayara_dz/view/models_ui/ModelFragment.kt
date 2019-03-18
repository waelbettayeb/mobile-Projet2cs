package com.waelkhelil.sayara_dz.view.models_ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.Model
import kotlinx.android.synthetic.main.model_fragment.*

class ModelFragment : Fragment() {

    companion object {
        fun newInstance() = ModelFragment()
    }

    private lateinit var viewModel: ModelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.model_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lBundle: Bundle? = arguments
        if (lBundle != null) {

        }
        val list:List<Model> = listOf(
            Model(0,"Toyota" ,""),
            Model(1,"Audi" ,""),
            Model(2,"BMW" ,""),
            Model(3,"Renault","" ),
            Model(4,"Renault","" ),
            Model(5,"Renault","" ),
            Model(6,"Mini","")
        )
//        rv_horizontal_list?. adapter = CardsListItemAdapter(list)
        var lLayoutManager = LinearLayoutManager(activity)
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
