package com.example.nextstep.data.retrofit

import com.example.nextstep.data.model.CareerUpdate
import com.example.nextstep.data.model.DetailRoadmapResponse
import com.example.nextstep.data.model.UserRoadmapResponse
import com.example.nextstep.data.model.PredictedJobResponse
import com.example.nextstep.data.model.RegisterRequest
import com.example.nextstep.data.model.RegisterResponse
import com.example.nextstep.data.model.RoadmapsResponse
import com.example.nextstep.data.model.SetProfileResponse
import com.example.nextstep.data.model.SetRoadmapResponse
import com.example.nextstep.data.model.SkillRequest
import com.example.nextstep.data.model.TemplateResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("api/auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    ///Header Authorization is Bearer Token
    @POST("api/predict")
    suspend fun getPrediction(
        @Header("Authorization") token: String,
        @Body skills: SkillRequest,
    ): PredictedJobResponse

    //user id
    @PUT("api/roadmaps/set/{id}")
    suspend fun setRoadmap(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body career: CareerUpdate
    ): SetRoadmapResponse

    //roadmap id
    @GET("api/roadmaps/{id}")
    suspend fun getRoadmapById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): DetailRoadmapResponse

    @GET("api/roadmaps")
    suspend fun getAllRoadmaps(
        @Header("Authorization") token: String,
    ): RoadmapsResponse

    //user id
    @GET("api/users/{id}")
    suspend fun getUserById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): UserRoadmapResponse


    @POST("api/details/{id}")
    suspend fun setProfile(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body requestBody: MultipartBody
    ): SetProfileResponse

    //user id
    @GET("api/details/{id}")
    suspend fun getDetailProfile(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): UserRoadmapResponse

    //user id
    @GET("api/generate/{id}")
    suspend fun generateTemplate(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): TemplateResponse
}

