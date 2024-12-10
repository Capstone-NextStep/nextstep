package com.example.nextstep.presentation.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextstep.data.repository.AppRepository
import com.example.nextstep.di.Injection

class CareerDetailViewModel(private val appRepository: AppRepository): ViewModel() {
    fun getRoadmapById(id: String) = appRepository.getRoadmapById(id)
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