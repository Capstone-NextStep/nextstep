package com.example.nextstep.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.nextstep.data.AuthResult
import com.example.nextstep.data.Result
import com.example.nextstep.data.model.RegisterResponse
import com.example.nextstep.data.model.UserSession
import com.example.nextstep.data.retrofit.ApiService
import com.example.nextstep.preference.AppPreference
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository private constructor(
    private val apiService: ApiService,
    private val appPreference: AppPreference
) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun register(
        name: String,
        email: String,
        password: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(name, email, password)
            if (response.user.userId.isNullOrEmpty()) {
                emit(Result.Success(response))
            } else {
                emit(Result.Error(response.message))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun login(email: String, password: String): AuthResult {
        return try {
            AuthResult.Loading
            val result = auth.signInWithEmailAndPassword(email, password).await()
            result.user?.let { firebaseUser ->
                val token = firebaseUser.getIdToken(true).await().token
                //save data to datastore
                appPreference.saveUserData(
                    UserSession(
                        uid = firebaseUser.uid,
                        email = firebaseUser.email ?: "",
                        displayName = firebaseUser.displayName,
                        token = token
                    )
                )
                AuthResult.Success(firebaseUser, token)
            } ?: AuthResult.Error("Login Failed")

        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Unknown error occurred")
        }
    }

    fun getSession(): LiveData<UserSession>{
        return appPreference.getUserData().asLiveData()
    }

    suspend fun logout() {
        auth.signOut()
        appPreference.clearUserData()
    }

    companion object {
        fun getInstance(
            apiService: ApiService,
            appPreference: AppPreference
        ): AuthRepository =
            AuthRepository(apiService, appPreference)
    }
}