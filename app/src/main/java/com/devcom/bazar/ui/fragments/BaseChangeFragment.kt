package com.devcom.bazar.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.devcom.bazar.MainActivity
import com.devcom.bazar.R
import com.devcom.bazar.utilits.APP_ACTIVITY
import com.devcom.bazar.utilits.hideKeyboard

open class BaseChangeFragment(layout: Int) : Fragment(layout) {
    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        APP_ACTIVITY.mAppDrawer.disableDrawer()
        hideKeyboard()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> change()
        }
        return true
    }

    open fun change() {

    }
}