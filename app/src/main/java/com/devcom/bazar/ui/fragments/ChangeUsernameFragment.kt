package com.devcom.bazar.ui.fragments

import com.devcom.bazar.R
import com.devcom.bazar.utilits.*
import kotlinx.android.synthetic.main.fragment_change_username.*
import java.util.*

class ChangeUsernameFragment : BaseChangeFragment(R.layout.fragment_change_username) {

    lateinit var mNewUsername:String

    override fun onResume() {
        super.onResume()
        settings_input_username.setText(USER.username)
    }

    override fun change() {
        mNewUsername = settings_input_username.text.toString().toLowerCase(Locale.getDefault())
        if (mNewUsername.isEmpty()) {
            showToast("Поле пустое")
        } else {
            REF_DATABASE_ROOT.child(CHILD_USER_NAME)
                .addListenerForSingleValueEvent(AppValueEventListener{
                    if(it.hasChild(mNewUsername)){
                        showToast("Пользователь с таким именем уже существует")
                    } else {
                        changeUsername()
                    }
                })
        }
    }

    private fun changeUsername() {
        REF_DATABASE_ROOT.child(NODE_USER_NAMES).child(mNewUsername).setValue(CURRENT_UID)
            .addOnCompleteListener{
                if(it.isSuccessful) {
                    updateCurrentUsername()
                }
            }
    }

    private fun updateCurrentUsername() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_USER_NAME)
            .setValue(mNewUsername)
            .addOnCompleteListener{
                if(it.isSuccessful) {
                    clearOldUsername()
                } else {
                    showToast(it.exception?.message.toString())
                }
            }
    }

    private fun clearOldUsername() {
       REF_DATABASE_ROOT.child(NODE_USER_NAMES).child(USER.username).removeValue()
           .addOnCompleteListener{
               if(it.isSuccessful) {
                   showToast(getString(R.string.toast_data_update))
                   fragmentManager?.popBackStack()
                   USER.username = mNewUsername
               } else {
                   showToast(it.exception?.message.toString())
               }
           }
    }
}