package com.example.nextstep.presentation.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextstep.data.Result
import com.example.nextstep.data.model.PredictedJobResponse
import com.example.nextstep.data.model.SkillRequest
import com.example.nextstep.data.repository.AppRepository
import com.example.nextstep.di.Injection


class JobPredictViewModel(private val appRepository: AppRepository) : ViewModel() {
    private val _result = MutableLiveData<Result<PredictedJobResponse>>()
    val result: LiveData<Result<PredictedJobResponse>> = _result

    fun getPrediction(skills: SkillRequest) = appRepository.getPrediction(skills)


}

class JobPredictViewModelFactory private constructor(private val appRepository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobPredictViewModel::class.java)) {
            return JobPredictViewModel(appRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: JobPredictViewModelFactory? = null
        fun getInstance(context: Context): JobPredictViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: JobPredictViewModelFactory(Injection.appRepository(context))
            }.also { instance = it }
    }
}