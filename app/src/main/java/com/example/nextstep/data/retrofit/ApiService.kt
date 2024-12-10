package com.example.nextstep.data.retrofit

import com.example.nextstep.data.model.CareerUpdate
import com.example.nextstep.data.model.DetailRoadmapResponse
import com.example.nextstep.data.model.UserRoadmapResponse
import com.example.nextstep.data.model.PredictedJobResponse
import com.example.nextstep.data.model.RegisterResponse
import com.example.nextstep.data.model.RoadmapsResponse
import com.example.nextstep.data.model.SetProfileResponse
import com.example.nextstep.data.model.SetRoadmapResponse
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
    @FormUrlEncoded
    @POST("api/auth/register")
    suspend fun register(
        @Field("displayName") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @POST("api/predict")
    suspend fun getPrediction(
        @Body skills: List<String>,
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


    //user id
    @Multipart
    @POST("api/details/{id}")
    suspend fun setProfile(
        @Path("id") id: String,
        @Part("name") name: RequestBody,
        @Part("age") age: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("currentPosition") currentPosition: RequestBody,
        @Part("institution") institution: RequestBody,
        @Part("major") major: RequestBody,
        @Part("bio") bio: RequestBody,
        @Part file: MultipartBody.Part,
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

//web api key firebase: AIzaSyDBfLKzI4qzrdo4lpOULT_PZO5yP_bIMgc

//contoh response dari login firebase
/*{
    "kind": "identitytoolkit#VerifyPasswordResponse",
    "localId": "LWEJArKA0OTyjzmmEZw0S7wihDp1",
    "email": "postman1@gmail.com",
    "displayName": "postman1",
    "idToken": "eyJhbGciOiJSUzI1NiIsImtpZCI6ImJkMGFlMTRkMjhkMTY1NzhiMzFjOGJlNmM4ZmRlZDM0ZDVlMWExYzEiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoicG9zdG1hbjEiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vbmV4dC1zdGVwLTQ0MjgwMSIsImF1ZCI6Im5leHQtc3RlcC00NDI4MDEiLCJhdXRoX3RpbWUiOjE3MzM3NjMzMzAsInVzZXJfaWQiOiJMV0VKQXJLQTBPVHlqem1tRVp3MFM3d2loRHAxIiwic3ViIjoiTFdFSkFyS0EwT1R5anptbUVadzBTN3dpaERwMSIsImlhdCI6MTczMzc2MzMzMCwiZXhwIjoxNzMzNzY2OTMwLCJlbWFpbCI6InBvc3RtYW4xQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJlbWFpbCI6WyJwb3N0bWFuMUBnbWFpbC5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwYXNzd29yZCJ9fQ.VG2aBUXHj7CUha3ChoufxGp0vt22M0RAhoUzIP3Z7K3jWOoikJGlAGn38sxP6Rwanlgxjed-7h7E56ems3__dIbg1cUx_U0bvszU4nzB-BU9XFL6Hp6ju9CYStbQFPy_Ar-PSvRJ-psedZtaYfHVZtNq-oP9XADy28Pa1AZywf2gWu-zZ9kPT9m0jTFKAR66BjqhouLx6IKS8jeKizCPkD0y_rIaPnORZ3uaMvRtBSMBQD1wUs8PLfEkWMx2xP3ji-1ExGHrblcvqhu4mUfZxELzC1GI-1y1eoq4nlgVs4roVpDJyGHNPyoJuHNCBw0dhmsckK-uGS-1yLR4khW9mw",
    "registered": true,
    "refreshToken": "AMf-vBzP0HdlfborrQmCh6C8p_SlOmfWhScG82wMyDQoFFzzG4aOSWzrYGNXZ4cTWK3qGT3Vs3jH-6r1dZUh7pMnIip7z7ClhL4wW7A-njPvHu9iejmy-XyOHY7Owp5TlccYJWstBGuwnG9ve4d8NpjdXiZI678jPZnJ0iYNddSk62hsBfiTGzUlzFVrQLuntiYkDgBRfD5NHn523wLE4WfTWHiyF3d8hbRwAzoC3v-iNDFt-pYciFo",
    "expiresIn": "3600"
}*/

//contoh upload multipart
/*
fun prepareUser Data(name: String, age: String, gender: String, imageFile: File): Pair<Map<String, RequestBody>, MultipartBody.Part> {
    // Prepare text fields
    val nameRequestBody = RequestBody.create("text/plain".toMediaType(), name)
    val ageRequestBody = RequestBody.create("text/plain".toMediaType(), age)
    val genderRequestBody = RequestBody.create("text/plain".toMediaType(), gender)

    // Prepare image file
    val requestFile = RequestBody.create("image/jpeg".toMediaType(), imageFile) // Adjust media type as needed
    val imagePart = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)

    // Create a map for text fields
    val textFieldsMap = mapOf(
        "name" to nameRequestBody,
        "age" to ageRequestBody,
        "gender" to genderRequestBody
    )

    return Pair(textFieldsMap, imagePart)
}*/
