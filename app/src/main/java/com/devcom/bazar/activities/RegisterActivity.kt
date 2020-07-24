package com.devcom.bazar.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.devcom.bazar.R
import com.devcom.bazar.databinding.ActivityRegisterBinding
import com.devcom.bazar.ui.fragments.EnterPhoneNumberFragment
import com.devcom.bazar.utilits.initFirebase
import com.devcom.bazar.utilits.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var mRegisterBinding: ActivityRegisterBinding
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mRegisterBinding.root)
        initFirebase()
    }

    override fun onStart() {
        super.onStart()
        mToolbar = mRegisterBinding.registerToolbar
        setSupportActionBar(mToolbar)
        title = getString(R.string.register_title_your_phone)
        replaceFragment(EnterPhoneNumberFragment(),false)
    }
}