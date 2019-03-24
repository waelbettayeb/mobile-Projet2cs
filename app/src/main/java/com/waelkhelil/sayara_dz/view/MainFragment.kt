package com.waelkhelil.sayara_dz.view

import android.app.Activity
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.view.LoginViewModel.AuthenticationState.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }


    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController(view)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                AUTHENTICATED -> showWelcomeMessage()
                UNAUTHENTICATED -> navController.navigate(R.id.fragment_login)
            }
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
    private fun showWelcomeMessage() {

    }
}
