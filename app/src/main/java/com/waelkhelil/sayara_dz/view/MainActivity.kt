package com.waelkhelil.sayara_dz.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.os.AsyncTask
import com.google.android.gms.auth.api.signin.*
import android.net.Uri
import android.preference.PreferenceManager
import com.facebook.AccessToken
import com.facebook.Profile
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

        //Facebook auth
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired

        //Google auth
        val acct:GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(application)

        if (!isSkipped && (acct == null) && !isLoggedIn ) {
            val intent = Intent(this, AppIntroActivity::class.java)
            startActivity(intent)
        }

        // TODO add user data class (singleton) and use it on a view model
        val lUserName:String?
        val lPersonPhoto: Uri?
        val lBundle:Bundle = Bundle()
        if(acct != null){
            lUserName  = acct?.getGivenName()
            lPersonPhoto = acct?.getPhotoUrl()
            lBundle.putString("user_name", lUserName)
            lBundle.putString("user_photo_url", lPersonPhoto.toString())
        }else if(isLoggedIn){
            val profile = Profile.getCurrentProfile()
            lUserName = profile.firstName
            lPersonPhoto = profile.getProfilePictureUri(100,100)
            lBundle.putString("user_name", lUserName)
            lBundle.putString("user_photo_url", lPersonPhoto.toString())
        }



        val lBottomNavigationView = findViewById<BottomNavigationView>(com.waelkhelil.sayara_dz.R.id.bottom_navigation)
        lBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

//      Adding the home fragment programmatically to R.id.fragment_container
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()


        val fragment = HomeFragment()
        fragment.arguments = lBundle
        fragmentTransaction.add(R.id.fragment_container, fragment, "home")
        fragmentTransaction.commit()
    }
    fun setFragment(
        fragment:Fragment,
        tag:String,
        backStackTag:String
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
}
