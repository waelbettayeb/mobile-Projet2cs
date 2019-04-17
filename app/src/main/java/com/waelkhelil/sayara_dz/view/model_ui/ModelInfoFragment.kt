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

        val list:List<PaintColor> = listOf(
            PaintColor("red", "#2196F3"),
            PaintColor("red", "#FF6050"),
            PaintColor("red", "#FF0E83"),
            PaintColor("red", "#839BFD"),
            PaintColor("red", "#DDE3FE")
        )
        val recyclerView = rv_colors_list
        recyclerView.adapter = ColorListAdapter(list)

    }
}
