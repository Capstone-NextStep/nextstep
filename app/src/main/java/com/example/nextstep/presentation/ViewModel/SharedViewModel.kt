package com.example.nextstep.presentation.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    fun setUserId(id: String){
        _userId.value = id
    }
}