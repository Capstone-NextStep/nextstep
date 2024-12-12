package com.example.nextstep.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.nextstep.data.Result
import com.example.nextstep.data.model.CareerUpdate
import com.example.nextstep.data.model.Data
import com.example.nextstep.data.model.PredictedJobsItem
import com.example.nextstep.data.model.RoadmapDetail
import com.example.nextstep.data.model.RoadmapsItem
import com.example.nextstep.data.model.SetRoadmapResponse
import com.example.nextstep.data.model.SkillRequest
import com.example.nextstep.data.model.TemplateResponse
import com.example.nextstep.data.model.UserDetail
import com.example.nextstep.data.model.UserSession
import com.example.nextstep.data.retrofit.ApiService
import com.example.nextstep.preference.AppPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File

class AppRepository private constructor(
    private val apiService: ApiService,
    private val appPreference: AppPreference
) {

    //getPrediction
    fun getPrediction(skills: SkillRequest): LiveData<Result<List<PredictedJobsItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getPrediction(skills)
            emit(Result.Success(response.predictedJobs))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            emit(Result.Error(errorBody.toString()))
        }
    }

    //setRoadmap
    fun setRoadmap(id: String, career: CareerUpdate): LiveData<Result<SetRoadmapResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.setRoadmap(id, career)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    //getRoadmapById
    fun getRoadmapById(id: String): LiveData<Result<RoadmapDetail>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getRoadmapById(id)
            emit(Result.Success(response.roadmap))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    //getAllRoadmaps
    fun getAllRoadmaps(): LiveData<Result<List<RoadmapsItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllRoadmaps()
            emit(Result.Success(response.roadmaps))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    //getUserRoadmapById
    fun getUserRoadmapById(userId: String): LiveData<Result<UserDetail>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUserById(userId)
            emit(Result.Success(response.user))
            CoroutineScope(Dispatchers.IO).launch {
                appPreference.saveUserCareer(response.user.career)
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    //generateTemplate
    fun generateTemplate(id: String): LiveData<Result<TemplateResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.generateTemplate(id)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun setProfile(
        imageFile: File,
        id: String,
        name: String,
        age: String,
        gender: String,
        currentPosition: String,
        institution: String,
        major: String,
        bio: String
    ): LiveData<Result<Data>> = liveData {
        emit(Result.Loading)
        val requestBody = MultipartBody.Builder().apply {
            setType(MultipartBody.FORM)
            addFormDataPart("name", name)
            addFormDataPart("age", age)
            addFormDataPart("gender", gender)
            addFormDataPart("currentPosition", currentPosition)
            addFormDataPart("institution", institution)
            addFormDataPart("major", major)
            addFormDataPart("bio", bio)
            addFormDataPart(
                "file",
                imageFile.name,
                imageFile.asRequestBody("image/jpeg".toMediaType())
            )
        }.build()

        try {
            val response = apiService.setProfile(
                id, requestBody
            )
            emit(Result.Success(response.data))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }


    }


    fun getUserId(): LiveData<String> {
        return appPreference.getUserId().asLiveData()
    }

    fun getSession(): LiveData<UserSession> {
        return appPreference.getUserData().asLiveData()
    }

    fun saveUserCareer(career: String) {
        CoroutineScope(Dispatchers.IO).launch {
            appPreference.saveUserCareer(career)
        }
    }

    fun getUserCareer(): LiveData<String> {
        return appPreference.getUserCareer().asLiveData()
    }

    companion object {

        fun getInstance(
            apiService: ApiService,
            appPreference: AppPreference
        ): AppRepository = AppRepository(apiService, appPreference)
    }
}