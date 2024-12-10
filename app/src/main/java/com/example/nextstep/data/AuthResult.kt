package com.example.nextstep.data

import com.google.firebase.auth.FirebaseUser

sealed class AuthResult {
    data class Success(
        val user: FirebaseUser,
        val token: String? = null
    ) : AuthResult()
    data class Error(val message: String) : AuthResult()
    object Loading : AuthResult()
}