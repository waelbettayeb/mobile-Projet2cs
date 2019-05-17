package com.waelkhelil.sayara_dz.view.home_ui

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.waelkhelil.sayara_dz.R


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }


    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
             return inflater.inflate(R.layout.fragment_home, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        prepareUi()

    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//        val fragmentManager = childFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//
//
//        val lHomeTopFragment= HomeTopFragment()
//        fragmentTransaction.add(R.id.lists_fragment_container, lHomeTopFragment)
//
//        val lBrandsHorziontalListFragment = HorizontalListFragment()
//        fragmentTransaction.add(R.id.lists_fragment_container, lBrandsHorziontalListFragment)
//
////      preparing the Models list
//        val lModelsHorziontalListFragment = HorizontalListFragment()
//        val lBundle= Bundle()
//
//        lBundle.putString("header", "Models")
//        lBundle.putBoolean("see_all_button_hidden", true)
//
//        lModelsHorziontalListFragment.arguments = lBundle
//
//        fragmentTransaction.add(R.id.lists_fragment_container, lModelsHorziontalListFragment)
//
//        val sharedPref =  PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
//        if(!sharedPref.getBoolean("is_connected", false)){
//            val lHomeSignInFragment = HomeSignInFragment()
//            fragmentTransaction.add(R.id.lists_fragment_container, lHomeSignInFragment)
//        }
//        fragmentTransaction.commit()
//    }

    fun prepareUi(){
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)

        val lUser = sharedPref.getString("user_name", R.string.msg_please_sign_in.toString())
        val lUserPhotoUrl = sharedPref.getString("photo_url","")
        val lUserNameTextView = view!!.
            findViewById<TextView>(R.id.text_user_name)
        val lUserProfileImageView = view!!.findViewById<ImageView>(R.id.image_user_profile_picture)

        lUserNameTextView.text = lUser
        lUserProfileImageView.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.fragment_user_content)
        )
        Glide
            .with(this)
            .load(lUserPhotoUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.user_icon)
            .into(lUserProfileImageView)
    }
}
