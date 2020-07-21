package com.devcom.bazar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.devcom.bazar.activities.RegisterActivity
import com.devcom.bazar.databinding.ActivityMainBinding
import com.devcom.bazar.ui.fragments.ChatFragment
import com.devcom.bazar.ui.objects.AppDrawer
import com.devcom.bazar.utilits.AUTH
import com.devcom.bazar.utilits.initFirebase
import com.devcom.bazar.utilits.replaceActivity
import com.devcom.bazar.utilits.replaceFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mMainToolbar: Toolbar
    lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            setSupportActionBar(mMainToolbar)
            mAppDrawer.create()
            replaceFragment(ChatFragment(), false)
        } else {
            replaceActivity(RegisterActivity())
        }

    }


    private fun initFields() {
        mMainToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mMainToolbar)
        initFirebase()
    }
}