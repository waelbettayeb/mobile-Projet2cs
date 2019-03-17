package com.waelkhelil.sayara_dz.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.User
import kotlinx.android.synthetic.main.fragment_home_top.*


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
        val lUserNameTextView = getView()!!.
            findViewById<TextView>(R.id.text_user_name)
        lUserNameTextView.setText(lUser)
        Glide
            .with(this)
            .load(lUserPhotoUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.user_icon)
            .into(getView()!!.findViewById<TextView>(R.id.image_user_profile_picture) as ImageView)    }

}
