package com.vanlife.android_app.presentation.ui.login

import com.google.firebase.auth.AuthResult

data class GoogleSignInState(
    val success: AuthResult? = null,
    val loading: Boolean = false,
    val error: String? = ""
)
