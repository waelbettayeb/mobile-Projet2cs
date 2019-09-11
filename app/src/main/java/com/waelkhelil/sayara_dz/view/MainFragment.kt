package com.waelkhelil.sayara_dz.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.SharedViewModel
import com.waelkhelil.sayara_dz.database.model.Version


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var navController: NavController
    private lateinit var sharedViewModel: SharedViewModel

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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lBottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        navController = findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(lBottomNavigationView, navController)

        lBottomNavigationView.getOrCreateBadge(R.id.notification_fragment)
        sharedViewModel.mCompareList.observe(viewLifecycleOwner, Observer<Set<Version>> {
            if (it.size > 1) {
                lBottomNavigationView.getOrCreateBadge(R.id.home_fragment)
            }else{
                lBottomNavigationView.removeBadge(R.id.home_fragment)
            }
        })

       /* sharedViewModel.getUserOrders("fm_bourouais@esi.dz")!!.observe(this, Observer<List<reservation>>
        {
            if ( it.size!=0) {
                showUserOrdersDialog("vous avez ${it.size} commandes acceptées")
            }
        })*/
    }


    @SuppressLint("ResourceType")
    private fun showUserOrdersDialog(message:String) {
        var dialogs = Dialog(this.activity!!)
        dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogs.setCancelable(false)
        dialogs.setContentView(com.waelkhelil.sayara_dz.R.layout.order_notification)
        val yesBtn = dialogs.findViewById(com.waelkhelil.sayara_dz.R.id.btn_ok) as Button
        val closeBtn = dialogs.findViewById(com.waelkhelil.sayara_dz.R.id.btn_close) as Button
        val alertMessage = dialogs.findViewById(com.waelkhelil.sayara_dz.R.id.tv_notification_message) as TextView

        alertMessage.text=message

        closeBtn.setOnClickListener {
            dialogs.hide()
        }
        dialogs.show()

    }


}