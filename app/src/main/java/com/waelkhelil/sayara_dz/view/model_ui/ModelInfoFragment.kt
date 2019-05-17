package com.waelkhelil.sayara_dz.view.model_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.model.PaintColor
import kotlinx.android.synthetic.main.fragment_model_info.*


class ModelInfoFragment : Fragment() {

    companion object {
        fun newInstance() = ModelInfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_model_info, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Description
        view?.findViewById<TextView>(R.id.tv_description)!!.text = "Nouvelle CLIO propose un niveau de qualité des matériaux inédit dans la catégorie, avec notamment le Smart cockpit immersif avec sa console haute"

        //Colors
        val colorsList:List<PaintColor> = listOf(
            PaintColor("red", "#2196F3"),
            PaintColor("red", "#FF6050"),
            PaintColor("red", "#FF0E83"),
            PaintColor("red", "#839BFD"),
            PaintColor("red", "#DDE3FE")
        )
        val ColorsRecyclerView = rv_colors_list
        ColorsRecyclerView.adapter = ColorListAdapter(colorsList)

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
