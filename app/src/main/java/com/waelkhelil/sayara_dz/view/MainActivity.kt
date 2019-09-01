    package com.waelkhelil.sayara_dz.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.view.login_ui.LoginViewModel


    class MainActivity : AppCompatActivity() {
    private val TAG : String = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewModel.authenticationState.observe(this,
            Observer<LoginViewModel.AuthenticationState> { authenticationState ->
            when (authenticationState) {


                LoginViewModel.AuthenticationState.UNAUTHENTICATED ->

                    navController.navigate(R.id.fragment_main)
              LoginViewModel.AuthenticationState.AUTHENTICATED ->{
                  val bundle = bundleOf("user_name" to viewModel.getUser())

                   navController.navigate(R.id.fragment_main,bundle)}
            }
        })







}}
