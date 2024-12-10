package com.example.nextstep.data.model

import com.google.gson.annotations.SerializedName

data class SetProfileResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String
)

data class Data(

	@field:SerializedName("institution")
	val institution: String,

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("major")
	val major: String,

	@field:SerializedName("currentPosition")
	val currentPosition: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("bio")
	val bio: String,

	@field:SerializedName("profileImageUrl")
	val profileImageUrl: String,

	@field:SerializedName("age")
	val age: String,

	@field:SerializedName("updatedAt")
	val updatedAt: CreatedAt
)


