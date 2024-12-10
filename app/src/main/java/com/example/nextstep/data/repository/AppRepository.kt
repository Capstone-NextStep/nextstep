package com.example.nextstep.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.nextstep.data.Result
import com.example.nextstep.data.model.CareerUpdate
import com.example.nextstep.data.model.PredictedJobResponse
import com.example.nextstep.data.model.RoadmapDetail
import com.example.nextstep.data.model.RoadmapsItem
import com.example.nextstep.data.model.SetRoadmapResponse
import com.example.nextstep.data.model.TemplateResponse
import com.example.nextstep.data.model.UserDetail
import com.example.nextstep.data.retrofit.ApiService
import retrofit2.HttpException

class AppRepository private constructor(
    private val apiService: ApiService
) {
    //getPrediction
    fun getPrediction(skills: List<String>): LiveData<Result<PredictedJobResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getPrediction(skills)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            emit(Result.Error(errorBody.toString()))
        }
    }

    //setRoadmap
    fun setRoadmap(id: String, career: CareerUpdate): LiveData<Result<SetRoadmapResponse>> = liveData {
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

    companion object {
        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(
            apiService: ApiService
        ): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(apiService)
            }.also { instance = it }

    }
}