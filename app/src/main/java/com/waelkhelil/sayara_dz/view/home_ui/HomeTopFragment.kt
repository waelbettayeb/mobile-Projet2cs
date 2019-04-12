package com.waelkhelil.sayara_dz.view.home_ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.waelkhelil.sayara_dz.R


class HomeTopFragment : Fragment() {

    companion object {
        fun newInstance() = HomeTopFragment()
    }


    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_top, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)

        val lUser = sharedPref.getString("user_name", R.string.msg_please_sign_in.toString())
        val lUserPhotoUrl = sharedPref.getString("photo_url","")
        val lUserNameTextView = view!!.
            findViewById<TextView>(R.id.text_user_name)
        lUserNameTextView.text = lUser
        Glide
            .with(this)
            .load(lUserPhotoUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.user_icon)
            .into(view!!.findViewById(R.id.image_user_profile_picture))
    }
}
