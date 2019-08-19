package com.waelkhelil.sayara_dz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.view.add_listing.AddListingDialogFragment
import com.waelkhelil.sayara_dz.view.configure_version.ConfigureDialogFragment
import kotlinx.android.synthetic.main.fragment_user_content.*


class UserContentFragment : Fragment() {

    companion object {
        fun newInstance() = UserContentFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_content, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_main_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController)
        toolbar.inflateMenu(R.menu.menu_user)

        efab_add_post.setOnClickListener {
            val bundle = Bundle()

            val dialog = AddListingDialogFragment()
            dialog.arguments = bundle

            val ft = fragmentManager!!.beginTransaction()
            dialog.show(ft, ConfigureDialogFragment.TAG)
        }
    }
}
