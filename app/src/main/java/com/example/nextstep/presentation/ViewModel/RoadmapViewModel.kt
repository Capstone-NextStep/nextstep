package com.example.nextstep.presentation.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextstep.data.repository.AppRepository
import com.example.nextstep.di.Injection

class RoadmapViewModel(private val appRepository: AppRepository): ViewModel() {
    fun getUserRoadmapById(userId: String) = appRepository.getUserRoadmapById(userId)

    fun getSession() = appRepository.getSession()
}

class RoadmapViewModelFactory private constructor(private val appRepository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoadmapViewModel::class.java)) {
            return RoadmapViewModel(appRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: RoadmapViewModelFactory? = null
        fun getInstance(context: Context): RoadmapViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: RoadmapViewModelFactory(Injection.appRepository(context))
            }.also { instance = it }
    }
}