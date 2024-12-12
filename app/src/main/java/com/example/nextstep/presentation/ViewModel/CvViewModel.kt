package com.example.nextstep.presentation.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextstep.data.repository.AppRepository
import com.example.nextstep.di.Injection

class CvViewModel(private val appRepository: AppRepository): ViewModel() {
    fun generateTemplate(userId: String, token: String) = appRepository.generateTemplate(userId, token)
}

class CvViewModelFactory private constructor(private val appRepository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CvViewModel::class.java)) {
            return CvViewModel(appRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: CvViewModelFactory? = null
        fun getInstance(context: Context): CvViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: CvViewModelFactory(Injection.appRepository(context))
            }.also { instance = it }
    }
}