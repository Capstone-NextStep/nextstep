package com.example.nextstep.data.model


data class UserSession(
    val uid: String = "",
    val email: String = "",
    val displayName: String? = null,
    val token: String? = null
)
