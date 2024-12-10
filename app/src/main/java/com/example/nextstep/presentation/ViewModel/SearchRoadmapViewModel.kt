package com.example.nextstep.presentation.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextstep.data.repository.AppRepository
import com.example.nextstep.di.Injection

class SearchRoadmapViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getAllRoadmaps() = appRepository.getAllRoadmaps()
}

class SearchRoadmapViewModelFactory private constructor(private val appRepository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchRoadmapViewModel::class.java)) {
            return SearchRoadmapViewModel(appRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: SearchRoadmapViewModelFactory? = null
        fun getInstance(context: Context): SearchRoadmapViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: SearchRoadmapViewModelFactory(Injection.appRepository(context))
            }.also { instance = it }
    }
}