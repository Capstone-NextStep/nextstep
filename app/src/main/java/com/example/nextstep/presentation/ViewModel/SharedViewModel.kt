package com.example.nextstep.presentation.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nextstep.data.model.PredictedJobsItem

class SharedViewModel: ViewModel() {
    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    private val _predictedResult = MutableLiveData<List<PredictedJobsItem>>()
    val predictedResult: LiveData<List<PredictedJobsItem>> = _predictedResult

    fun setPredictedResult(result: List<PredictedJobsItem>){
        _predictedResult.value = result
    }

    fun setUserId(id: String){
        _userId.value = id
    }
}