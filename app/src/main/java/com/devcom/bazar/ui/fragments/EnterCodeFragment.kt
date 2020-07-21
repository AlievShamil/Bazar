package com.devcom.bazar.ui.fragments

import com.devcom.bazar.MainActivity
import com.devcom.bazar.R
import com.devcom.bazar.activities.RegisterActivity
import com.devcom.bazar.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment(val phoneNumber: String, val id: String) : BaseFragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNumber
        registerCodeInput.addTextChangedListener(AppTextWatcher {
            val codeText: String = registerCodeInput.text.toString()
            if (codeText.length == 6) enterCode()
        })
    }

    private fun enterCode() {
        val code = registerCodeInput.text.toString()
        val credential = PhoneAuthProvider.getCredential(id,code)
        AUTH.signInWithCredential(credential).addOnCompleteListener{
            if (it.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dataMap = mutableMapOf<String, Any>()
                dataMap[CHILD_ID] = uid
                dataMap[CHILD_PHONE] = phoneNumber
                dataMap[CHILD_USER_NAME] = uid

                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dataMap)
                    .addOnCompleteListener{
                    if(it.isSuccessful) {
                        showToast("Добро пожаловать")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    }  else showToast(it.exception?.message.toString())
                }

               } else {
                showToast(it.exception?.message.toString())
            }
        }
    }
}