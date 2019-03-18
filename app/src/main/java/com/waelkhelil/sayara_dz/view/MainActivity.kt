package com.waelkhelil.sayara_dz.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.os.AsyncTask
import android.preference.PreferenceManager
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.view.home_ui.HomeFragment


class MainActivity : AppCompatActivity() {
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                setFragment(HomeFragment(), "home")
            }
            R.id.navigation_search -> {
                setFragment(SearchFragment(), "search")
            }
//            R.id.navigation_add_listing-> {
////                TODO: User_Authentication
//                return@OnNavigationItemSelectedListener false
//            }
            R.id.navigation_notification -> {
//                TODO: User_Authentication
                return@OnNavigationItemSelectedListener false
            }
            R.id.navigation_user -> {
//                TODO: User_Authentication
                return@OnNavigationItemSelectedListener false

            }
        }
        true
    }
    class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            handler()
            return null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.waelkhelil.sayara_dz.R.layout.activity_main)

        val sharedPref =  PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val defaultValue = false
        val isSkipped = sharedPref.getBoolean(getString(R.string.skip_key), defaultValue)
        val isConnected = sharedPref.getBoolean("is_connected", defaultValue)


        if (!isSkipped && !isConnected) {
            switchToSignInActivity()
        }

        val lBottomNavigationView = findViewById<BottomNavigationView>(com.waelkhelil.sayara_dz.R.id.bottom_navigation)
        lBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

//      Adding the home fragment programmatically to R.id.fragment_container
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragment = HomeFragment()
        fragmentTransaction.add(R.id.fragment_container, fragment, "home")
        fragmentTransaction.commit()
    }
    fun setFragment(
        fragment:Fragment,
        tag:String,
        backStackTag:String?
    ){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag).addToBackStack(backStackTag)
        fragmentTransaction.commit()
    }
    fun setFragment(
        fragment:Fragment,
        tag:String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag)
        fragmentTransaction.commit()
    }
    fun switchToSignInActivity(){
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext).edit()
        sharedPref.clear()
        val intent = Intent(this, AppIntroActivity::class.java)
        startActivity(intent)
    }
}
