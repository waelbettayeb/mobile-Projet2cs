package com.waelkhelil.sayara_dz.view.model_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.model.PaintColor
import kotlinx.android.synthetic.main.fragment_model_info.*
import androidx.recyclerview.widget.DividerItemDecoration
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

        val colorsList:List<PaintColor> = listOf(
            PaintColor("red", "#2196F3"),
            PaintColor("red", "#FF6050"),
            PaintColor("red", "#FF0E83"),
            PaintColor("red", "#839BFD"),
            PaintColor("red", "#DDE3FE")
        )
        val ColorsRecyclerView = rv_colors_list
        ColorsRecyclerView.adapter = ColorListAdapter(colorsList)

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
        val layoutManager = GridLayoutManager(context,3)
        val recyclerView = rv_image_list
        recyclerView.adapter = ImageAdapter(list)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.hasFixedSize()
    }
}
