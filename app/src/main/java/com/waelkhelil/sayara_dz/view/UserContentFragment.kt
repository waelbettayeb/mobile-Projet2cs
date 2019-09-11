package com.waelkhelil.sayara_dz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.model.Listing
import com.waelkhelil.sayara_dz.view.add_listing.AddListingDialogFragment
import com.waelkhelil.sayara_dz.view.configure_version.ConfigureDialogFragment
import com.waelkhelil.sayara_dz.view.home_ui.ListingListItemAdapter
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.android.synthetic.main.fragment_user_content.*

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController

import com.waelkhelil.sayara_dz.view.login_ui.LoginViewModel



class UserContentFragment : Fragment() {
     lateinit var navController:NavController
    companion object {
        fun newInstance() = UserContentFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.waelkhelil.sayara_dz.R.layout.fragment_user_content, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(com.waelkhelil.sayara_dz.R.id.toolbar)
         navController = Navigation.findNavController(requireActivity(), com.waelkhelil.sayara_dz.R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)

        toolbar.inflateMenu(R.menu.menu_user)

        efab_add_post.setOnClickListener {
            val bundle = Bundle()

            val dialog = AddListingDialogFragment()
            dialog.arguments = bundle

            val ft = fragmentManager!!.beginTransaction()
            dialog.show(ft, ConfigureDialogFragment.TAG)
        }
/*
        toolbar.inflateMenu(com.waelkhelil.sayara_dz.R.menu.menu_user)
        toolbar.menu.getItem(1).setOnMenuItemClickListener {
            onOptionsItemSelected(it)


        }


*/
    }
    override

    fun onOptionsItemSelected( item : MenuItem):Boolean {
        var viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        when (item.itemId) {
        R.id.action_logout-> {

                viewModel.logOut()





                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }


    }


}
