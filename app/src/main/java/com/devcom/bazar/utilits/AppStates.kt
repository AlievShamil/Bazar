package com.devcom.bazar.utilits

enum class AppStates(val state: String) {
    ONLINE("в сети"),
    OFFLINE("был недавно"),
    TYPING("печатает");

    companion object{
        fun updateState(appStates: AppStates) {
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
                .child(CHILD_STATUS).setValue(appStates.state)
//                .addOnSuccessListener { USER.status = appStates.state }
                .addOnFailureListener{showToast(it.message.toString())}
        }
    }
}