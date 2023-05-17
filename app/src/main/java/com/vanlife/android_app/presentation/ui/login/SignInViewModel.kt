package com.vanlife.android_app.presentation.ui.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.vanlife.android_app.data.repository.AuthRepository
import com.vanlife.android_app.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    // Sign in with email and password

    private val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUser(email, password).collect { result ->
            when (result) {
                is Resource.Success -> _signInState.send(SignInState(success = "Sign In Successful"))
                is Resource.Loading -> _signInState.send(SignInState(loading = true))
                is Resource.Error -> _signInState.send(SignInState(error = result.message))
            }
        }
    }

    // Sign in with Google

    private val _googleState = mutableStateOf(GoogleSignInState())
    val googleState: State<GoogleSignInState> = _googleState

    fun googleSignIn(credential: AuthCredential) = viewModelScope.launch {
        repository.googleSignIn(credential).collect { result ->
            when (result) {
                is Resource.Success -> _googleState.value = GoogleSignInState(success = result.data)
                is Resource.Loading -> _googleState.value = GoogleSignInState(loading = true)
                is Resource.Error -> _googleState.value = GoogleSignInState(error = result.message)
            }
        }
    }
}