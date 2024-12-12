package com.example.nextstep.presentation.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nextstep.data.model.CareerUpdate
import com.example.nextstep.data.repository.AppRepository
import com.example.nextstep.di.Injection
import kotlinx.coroutines.launch

class CareerDetailViewModel(private val appRepository: AppRepository): ViewModel() {
    fun getRoadmapById(id: String, token: String) = appRepository.getRoadmapById(id, token)

    fun setUserRoadmap(userId: String, token: String, career: CareerUpdate) = appRepository.setRoadmap(userId, token, career)

    fun getUserId() = appRepository.getUserId()

    fun saveUserCareer(career: String) {
        viewModelScope.launch {
            appRepository.saveUserCareer(career)
        }
    }
}

class CareerDetailViewModelFactory private constructor(private val appRepository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CareerDetailViewModel::class.java)) {
            return CareerDetailViewModel(appRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: CareerDetailViewModelFactory? = null
        fun getInstance(context: Context): CareerDetailViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: CareerDetailViewModelFactory(Injection.appRepository(context))
            }.also { instance = it }
    }
}