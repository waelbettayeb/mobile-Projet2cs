package com.waelkhelil.sayara_dz.view.home_ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.view.MainActivity


class HomeSignInFragment : Fragment() {

    companion object {
        fun newInstance() = HomeSignInFragment()
    }


    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val signInButton = getView()?.findViewById<Button>(R.id.button_sign_in)
        signInButton?.setOnClickListener {
            (activity as MainActivity).switchToSignInActivity()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
