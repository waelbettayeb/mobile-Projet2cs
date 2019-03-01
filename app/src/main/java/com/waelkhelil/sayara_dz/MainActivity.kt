package com.waelkhelil.sayara_dz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lBottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        lBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

//      Adding the home fragment programmatically to R.id.fragment_container
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragment = HomeFragment()
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        lateinit var  selectedFragement:Fragment
        when (item.itemId) {
            R.id.navigation_home -> {
                selectedFragement = HomeFragment()
            }
            R.id.navigation_search -> {
                selectedFragement = SearchFragment()
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
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, selectedFragement)
        fragmentTransaction.commit()
        true
    }
}
