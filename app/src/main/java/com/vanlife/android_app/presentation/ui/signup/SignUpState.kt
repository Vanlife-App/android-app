package com.vanlife.android_app.presentation.ui.signup

data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess: String? = null,
    val isError: String? = ""
)