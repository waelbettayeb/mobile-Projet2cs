package com.waelkhelil.sayara_dz.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.waelkhelil.sayara_dz.R

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lBottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
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
        backStackTag:String){
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
}
