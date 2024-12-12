package com.example.nextstep.presentation.ViewModel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nextstep.data.repository.AppRepository
import com.example.nextstep.di.Injection
import java.io.File

class ProfileViewModel(private val appRepository: AppRepository) : ViewModel() {
    var currentImageUri: Uri? = null

    fun getUserId() = appRepository.getUserId()

    fun setProfile(
        imageFile: File,
        id: String,
        name: String,
        age: String,
        gender: String,
        currentPosition: String,
        institution: String,
        major: String,
        bio: String,
        token: String
    ) = appRepository.setProfile(
        imageFile = imageFile,
        id = id,
        name = name,
        age = age,
        gender = gender,
        currentPosition = currentPosition,
        institution = institution,
        major = major,
        bio = bio,
        token = token
    )
}

class ProfileViewModelFactory private constructor(private val appRepository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(appRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ProfileViewModelFactory? = null
        fun getInstance(context: Context): ProfileViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ProfileViewModelFactory(Injection.appRepository(context))
            }.also { instance = it }
    }
}