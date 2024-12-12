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
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @POST("api/auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @POST("api/predict")
    suspend fun getPrediction(
        @Body skills: SkillRequest,
    ): PredictedJobResponse

    //user id
    @PUT("api/roadmaps/set/{id}")
    suspend fun setRoadmap(
        @Path("id") id: String,
        @Body career: CareerUpdate
    ): SetRoadmapResponse

    //roadmap id
    @GET("api/roadmaps/{id}")
    suspend fun getRoadmapById(
        @Path("id") id: String
    ): DetailRoadmapResponse

    @GET("api/roadmaps")
    suspend fun getAllRoadmaps(): RoadmapsResponse

    //user id
    @GET("api/users/{id}")
    suspend fun getUserById(
        @Path("id") id: String
    ): UserRoadmapResponse


    @POST("api/details/{id}")
    suspend fun setProfile(
        @Path("id") id: String,
        @Body requestBody: MultipartBody
    ): SetProfileResponse

    //user id
    @GET("api/details/{id}")
    suspend fun getDetailProfile(
        @Path("id") id: String
    ): UserRoadmapResponse

    //user id
    @GET("api/generate/{id}")
    suspend fun generateTemplate(
        @Path("id") id: String
    ): TemplateResponse
}

