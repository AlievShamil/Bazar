package com.devcom.bazar.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.devcom.bazar.MainActivity
import com.devcom.bazar.R
import com.devcom.bazar.activities.RegisterActivity
import com.devcom.bazar.databinding.FragmentSettingsBinding
import com.devcom.bazar.utilits.AUTH
import com.devcom.bazar.utilits.USER
import com.devcom.bazar.utilits.replaceActivity
import com.devcom.bazar.utilits.replaceFragment
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    private fun initFields() {
        settings_username.text = USER.username
        settings_status.text = USER.status
        settings_phone_number.text = USER.phone
        settings_full_name.text = USER.fullname
        settings_bio.text = USER.bio
        settings_btn_change_username.setOnClickListener{
            replaceFragment(ChangeUsernameFragment())
        }
        settings_btn_change_bio.setOnClickListener{
            replaceFragment(ChangeBioFragment())
        }
        settings_change_photo.setOnClickListener{
            changePhotoUser()
        }
    }

    private fun changePhotoUser() {
        CropImage.activity()
            .setAspectRatio(1,1)
            .setRequestedSize(600,600)
            .setCropShape(CropImageView.CropShape.OVAL)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
            R.id.settings_menu_change_name -> {
                replaceFragment(ChangeNameFragment())
            }
        }
        return true
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}