package com.example.nextstep.presentation.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nextstep.data.AuthResult
import com.example.nextstep.data.repository.AuthRepository
import com.example.nextstep.di.Injection
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<AuthResult>()
    val loginResult: MutableLiveData<AuthResult> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = authRepository.login(email, password)
            _loginResult.value = result
            /*if (result is AuthResult.Success) {
                _currentUser.value = result.user
                _currentToken.value = result.token
            }*/
        }
    }
    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

    fun getSession() = authRepository.getSession()

    fun register(name: String, email: String, password: String) =
        authRepository.register(name, email, password)

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