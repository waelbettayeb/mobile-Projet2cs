package com.waelkhelil.sayara_dz.view.home_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.waelkhelil.sayara_dz.R
import kotlinx.android.synthetic.main.fragment_listing.*

class ListingFragment : Fragment() {

    companion object {
        fun newInstance() = ListingFragment()

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)

        view.findViewById<TextView>(R.id.tv_title).text = "Clio 5"
        view.findViewById<TextView>(R.id.tv_price).text = "2 000 K DA"
        view.findViewById<TextView>(R.id.tv_date).text = "09/09/2019"
        view.findViewById<TextView>(R.id.rv_description).text = "nice car"
        Glide
            .with(this)
            .load("https://images.app.goo.gl/1aknjVx6mGhR6cFbA")
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.user_icon)
            .into(image_user_profile_picture)

    }
    }
