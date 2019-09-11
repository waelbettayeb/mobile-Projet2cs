package com.waelkhelil.sayara_dz.model_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.waelkhelil.sayara_dz.R
import kotlinx.android.synthetic.main.dialog_model_info.*



class ModelInfoFragment : Fragment() {

   lateinit  var colorList:List<String>

    companion object {
        fun newInstance() = ModelInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO : check if its the right fragment
        return inflater.inflate(R.layout.fragment_model, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Description
        view?.findViewById<TextView>(R.id.tv_description)!!.text = "Nouvelle CLIO propose un niveau de qualité des matériaux inédit dans la catégorie, avec notamment le Smart cockpit immersif avec sa console haute"


        val ColorsRecyclerView = rv_colors_list
        ColorsRecyclerView.adapter = ColorListAdapter(colorList)



        //Options
        val OptionList:List<String> = listOf(
            "Jantes alliage 16'' Philia diamantée noir",
            "Personnalisation Intérieure Rouge",
            "Pack Attelage escamotable semi-électrique - 13B - avec pré-disposition usine",
            "Roue de secours tôle"
        )
        val OptionsRecyclerView = rv_options_list
        OptionsRecyclerView.adapter = OptionListAdapter(OptionList)
    }
}
