package com.waelkhelil.sayara_dz.view.home_ui

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import com.waelkhelil.sayara_dz.SharedViewModel
import com.waelkhelil.sayara_dz.database.model.Version
import com.waelkhelil.sayara_dz.view.compare.CompareFragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment( ) : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit  var user:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Share data between fragments
        sharedViewModel = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

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

        sharedViewModel.mCompareList.observe(viewLifecycleOwner, Observer<Set<Version>> {
            if (it.size > 1) {
                efab_comparison_list.visibility = View.VISIBLE
            }else{
                efab_comparison_list.visibility = View.GONE
            }
        })
        efab_comparison_list.setOnClickListener {
            val lCompareFragment= CompareFragment()
            fragmentManager?.let { it1 -> lCompareFragment.show(it1, CompareFragment.TAG) }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        prepareUi()

    }

    fun prepareUi(){
        val sharedPref = context!!.getSharedPreferences("userInfo",MODE_PRIVATE)

       val lUser = sharedPref.getString("fb_last_name", "Marwa")
        val lUserPhotoUrl = sharedPref.getString("fb_profileURL","")
        val lUserNameTextView = view!!.findViewById<TextView>(R.id.text_user_name)
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
