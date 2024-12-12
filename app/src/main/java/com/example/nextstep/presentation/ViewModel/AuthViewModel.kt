package com.example.nextstep.presentation.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nextstep.data.AuthResult
import com.example.nextstep.data.Result
import com.example.nextstep.data.model.RegisterResponse
import com.example.nextstep.data.repository.AuthRepository
import com.example.nextstep.di.Injection
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<AuthResult>()
    val loginResult: LiveData<AuthResult> = _loginResult

    private val _registerResult = MutableLiveData<Result<RegisterResponse>>()
    val registerResult: LiveData<Result<RegisterResponse>> = _registerResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = authRepository.login(email, password)
            _loginResult.value = result
        }
    }
    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

    fun getSession() = authRepository.getSession()

    fun getUserCareer() = authRepository.getUserCareer()

    fun register(name: String, email: String, password: String) = authRepository.register(name, email, password)

    /*fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            val result = authRepository.register(name, email, password)
            _registerResult.value = result
        }
    }*/


}

class AuthViewModelFactory(private val authRepository: AuthRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(authRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        fun getInstance(context: Context): AuthViewModelFactory =
            AuthViewModelFactory(Injection.authRepository(context))
    }
}