package com.waelkhelil.sayara_dz.view.configure_version

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.waelkhelil.sayara_dz.R


class ConfigureVersionFragment : Fragment() {

    companion object {
        fun newInstance() = ConfigureVersionFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_configure_version, container, false)

    }
}
