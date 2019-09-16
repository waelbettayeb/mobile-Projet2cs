package com.waelkhelil.sayara_dz.view.model_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.waelkhelil.sayara_dz.R
import kotlinx.android.synthetic.main.dialog_model_info.*


class ModelInfoDialogFragment : DialogFragment() {

    companion object {
        const val TAG: String = "ModelInfoDialogFragment"
        fun newInstance() = ModelInfoDialogFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_model_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_clear_black_24dp)
        toolbar.setNavigationOnClickListener {
            dismissAllowingStateLoss()
        }
        toolbar.title = resources.getString(R.string.information)

        //Images
        val list:List<String> = listOf(
            "https://img-4.linternaute.com/Yx38zdz-i1pZVfB3IMBKpTh3UDc=/620x/smart/332c5130da1c4dc391d1dbf56a422b02/ccmcms-linternaute/11076581.jpg",
            "https://www.automobile-magazine.fr/asset/cms/650x407/153411/config/106932/plutot-conservatrice-a-lexterieur-la-renault-clio-5-change-en-revanche-totalement-dans-lhabitacle.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTe0i9HBaQSWuVjxU37vnZr2cpv0f65YpxprjO7zrtagWUcbSJapw",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjrpIhu08Pv0Sg8RGqETU3PPOZiUBaF74EEAnEJz5V5Cke4k1p",
            ""
        )
        val gridLayoutManager = GridLayoutManager(context,3)
        val lImagesRecyclerView = rv_image_list
        lImagesRecyclerView.adapter = ImageAdapter(list)
        lImagesRecyclerView.layoutManager = gridLayoutManager
        lImagesRecyclerView.setHasFixedSize(true)
        lImagesRecyclerView.hasFixedSize()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Description
        view?.findViewById<TextView>(R.id.tv_description)!!.text = "Nouvelle CLIO propose un niveau de qualité des matériaux inédit dans la catégorie, avec notamment le Smart cockpit immersif avec sa console haute"

        //Colors
        val colorsList:List<String> = listOf(
            /*PaintColor("red", "#2196F3",0),
            PaintColor("red", "#FF6050",100),
            PaintColor("red", "#FF0E83",200),
            PaintColor("red", "#839BFD",200),
            PaintColor("red", "#DDE3FE",400)*/
        )

        val lColorsRecyclerView = rv_colors_list
        lColorsRecyclerView.adapter = ColorListAdapter(colorsList)

        //Options
        val lOptionList:List<String> = listOf(
            "Jantes alliage 16'' Philia diamantée noir",
            "Personnalisation Intérieure Rouge",
            "Pack Attelage escamotable semi-électrique - 13B - avec pré-disposition usine",
            "Roue de secours tôle"
        )
        val lOptionsRecyclerView = rv_options_list
        lOptionsRecyclerView.adapter = OptionListAdapter(lOptionList)
    }


    override fun onStart() {
        super.onStart()
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog?.window?.setLayout(width, height)
    }
}
