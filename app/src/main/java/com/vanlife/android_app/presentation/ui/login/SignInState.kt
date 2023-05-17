package com.vanlife.android_app.presentation.ui.login

data class SignInState(
    val success: String? = null,
    val loading: Boolean = false,
    val error: String? = ""
)