package com.vanlife.android_app.data.repository

import com.google.firebase.auth.AuthCredential
import com.vanlife.android_app.common.Resource
import kotlinx.coroutines.flow.Flow
import com.google.firebase.auth.AuthResult

interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun googleSignIn(credential: AuthCredential): Flow<Resource<AuthResult>>
}