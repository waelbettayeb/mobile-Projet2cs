package com.waelkhelil.sayara_dz.view.model_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.model.PaintColor
import kotlinx.android.synthetic.main.fragment_model_info.*
import androidx.recyclerview.widget.GridLayoutManager


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
        view?.findViewById<TextView>(R.id.tv_description)!!.setText("Nouvelle CLIO propose un niveau de qualité des matériaux inédit dans la catégorie, avec notamment le Smart cockpit immersif avec sa console haute, l’accoudoir coulissant et les écrans personnalisables.")
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
        //Images
        val list:List<String> = listOf(
            "https://img-4.linternaute.com/Yx38zdz-i1pZVfB3IMBKpTh3UDc=/620x/smart/332c5130da1c4dc391d1dbf56a422b02/ccmcms-linternaute/11076581.jpg",
            "https://www.automobile-magazine.fr/asset/cms/650x407/153411/config/106932/plutot-conservatrice-a-lexterieur-la-renault-clio-5-change-en-revanche-totalement-dans-lhabitacle.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTe0i9HBaQSWuVjxU37vnZr2cpv0f65YpxprjO7zrtagWUcbSJapw",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjrpIhu08Pv0Sg8RGqETU3PPOZiUBaF74EEAnEJz5V5Cke4k1p",
            "",
            "",
            "",
            ""
        )
        val gridLayoutManager = GridLayoutManager(context,3)
        val lImagesRecyclerView = rv_image_list
        lImagesRecyclerView.adapter = ImageAdapter(list)
        lImagesRecyclerView.layoutManager = gridLayoutManager
        lImagesRecyclerView.setHasFixedSize(true)
        lImagesRecyclerView.hasFixedSize()
    }
}
