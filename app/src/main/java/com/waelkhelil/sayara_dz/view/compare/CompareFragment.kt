package com.waelkhelil.sayara_dz.view.compare

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.SharedViewModel
import com.waelkhelil.sayara_dz.database.model.Option
import com.waelkhelil.sayara_dz.database.model.Version
import kotlinx.android.synthetic.main.fragment_home.*

class CompareFragment(): BottomSheetDialogFragment() {

    companion object {
        const val TAG = "CompareFragment"
        fun newInstance() = CompareFragment()
    }
    private lateinit var dialog : BottomSheetDialog
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var tableLayout: TableLayout

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
        tableLayout = view.findViewById(R.id.table_layout)
        sharedViewModel.mCompareList.observe(viewLifecycleOwner, Observer<Set<Version>> {
            tableLayout.removeAllViews()
            createTable(it)
        })
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
    private fun createTable(versionsList:Set<Version>) {
        val optionSet = mutableSetOf<Option>()
//      Create header
        val row = TableRow(context)
        row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        var lTextView = TextView(context)
        lTextView.text = getString(R.string.options)
        row.addView(lTextView)
        versionsList.forEach {
            lTextView = TextView(context)
            lTextView.text = it.name
            row.addView(lTextView)
            optionSet.addAll(it.compatibleOptions)
        }
        tableLayout.addView(row)

        //Create Content
        optionSet.forEach {
            val row = TableRow(context)
            lTextView = TextView(context)
            lTextView.text = it.name
            row.addView(lTextView)
            versionsList.forEach { v ->
                lTextView = TextView(context)
                if (v.compatibleOptions.contains(it))
                    lTextView.text = getString(R.string.yes)
                else
                    lTextView.text = getString(R.string.no)
                row.addView(lTextView)
            }
            tableLayout.addView(row)
        }
    }
}