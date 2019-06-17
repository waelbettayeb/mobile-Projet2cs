package com.waelkhelil.sayara_dz.view.compare

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.SharedViewModel
import com.waelkhelil.sayara_dz.view.model_ui.ModelViewModel

class CompareFragment: BottomSheetDialogFragment() {

    companion object {
        const val TAG = "CompareFragment"
        fun newInstance() = CompareFragment()
    }
    private lateinit var dialog : BottomSheetDialog
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
        return inflater.inflate(R.layout.fragment_expanded_compare, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_compare)
        toolbar.setOnMenuItemClickListener { item: MenuItem ->  this.onOptionsItemSelected(item)}

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_clear) {
            sharedViewModel.freeVersions()
            dismissAllowingStateLoss()
            true
        } else super.onOptionsItemSelected(item)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        return dialog
    }
    override fun onStart() {
        super.onStart()
        dialog.behavior.state = STATE_EXPANDED
    }

}