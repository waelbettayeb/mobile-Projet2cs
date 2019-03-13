package com.waelkhelil.sayara_dz.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.waelkhelil.sayara_dz.R

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ModelViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
