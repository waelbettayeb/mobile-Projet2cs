package com.waelkhelil.sayara_dz.view.home_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.model.AdResponse
import kotlinx.android.synthetic.main.fragment_listing.*

class ListingFragment : Fragment() {

    companion object {
        fun newInstance() = ListingFragment()

    }
    private lateinit var  current_ad:AdResponse
    private lateinit var viewModel:HomeViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        //current_ad=viewModel.adsList.get(arguments!!.getInt("adId"))
        viewModel.getAlAds().observe(this, Observer<List<AdResponse>> {

                  current_ad=it.get(arguments!!.getInt("adId"))
            view.findViewById<TextView>(R.id.tv_couleur).text = current_ad.couleur.get("Nom_Couleur").asString
            view.findViewById<TextView>(R.id.tv_title).text = current_ad.version.get("Nom_Version").asString
            view.findViewById<TextView>(R.id.tv_price).text = current_ad.minPrice.toString()+" DZD"
            view.findViewById<TextView>(R.id.tv_date).text = current_ad.date
            view.findViewById<TextView>(R.id.rv_description).text = current_ad.description
        })

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)


        Glide
            .with(this)
            .load("https://images.app.goo.gl/1aknjVx6mGhR6cFbA")
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.user_icon)
            .into(image_user_profile_picture)

    }
    }
