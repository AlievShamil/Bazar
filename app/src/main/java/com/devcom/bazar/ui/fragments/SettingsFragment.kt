package com.devcom.bazar.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.devcom.bazar.MainActivity
import com.devcom.bazar.R
import com.devcom.bazar.activities.RegisterActivity
import com.devcom.bazar.databinding.FragmentSettingsBinding
import com.devcom.bazar.utilits.AUTH
import com.devcom.bazar.utilits.replaceActivity

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {
    private lateinit var mBinding: FragmentSettingsBinding

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
        }
        return true
    }
}