    package com.waelkhelil.sayara_dz.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.os.AsyncTask
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.view.home_ui.HomeFragment


class MainActivity : AppCompatActivity() {
    private val TAG : String = "MainActivity"


//    class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
//        override fun doInBackground(vararg params: Void?): Void? {
//            handler()
//            return null
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewModel.authenticationState.observe(this,
            Observer<LoginViewModel.AuthenticationState> { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.UNAUTHENTICATED ->
                    navController.navigate(R.id.fragment_login)
//                LoginViewModel.AuthenticationState.AUTHENTICATED ->
//                    navController.navigate(R.id.action_global_main_nav_graph)
            }
        })

//
//        val sharedPref =  PreferenceManager.getDefaultSharedPreferences(applicationContext)
//        val defaultValue = false
//        val isSkipped = sharedPref.getBoolean(getString(R.string.skip_key), defaultValue)
//        val isConnected = sharedPref.getBoolean("is_connected", defaultValue)

//
//
//        if (!isSkipped && !isConnected) {
//            switchToSignInActivity()
//        }
//

//
////      Adding the home fragment programmatically to R.id.fragment_container
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//
//        val fragment = HomeFragment()
////        fragmentTransaction.add(R.id.fragment_container, fragment, "home")
////        fragmentTransaction.commit()
    }

    //    fun setFragment(
//        fragment:Fragment,
//        tag:String,
//        backStackTag:String?
//    ){
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
////        fragmentTransaction.replace(R.id.fragment_container, fragment, tag).addToBackStack(backStackTag)
////        fragmentTransaction.commit()
//    }
//    fun setFragment(
//        fragment:Fragment,
//        tag:String){
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
////        fragmentTransaction.replace(R.id.fragment_container, fragment, tag)
////        fragmentTransaction.commit()
//    }
//    fun switchToSignInActivity(){
//        val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext).edit()
//        sharedPref.clear()
//        val intent = Intent(this, AppIntroActivity::class.java)
//        startActivity(intent)
//    }



}
