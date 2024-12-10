package com.example.nextstep.data.model

import com.google.gson.annotations.SerializedName

data class UserRoadmapResponse(

	@field:SerializedName("user")
	val user: UserDetail
)

data class RoadmapProgressItem(

	@field:SerializedName("step")
	val step: String,

	@field:SerializedName("isDone")
	val isDone: Boolean
)


data class UserDetail(

	@field:SerializedName("createdAt")
	val createdAt: CreatedAt,

	@field:SerializedName("career")
	val career: String,

	@field:SerializedName("roadmapProgress")
	val roadmapProgress: List<RoadmapProgressItem>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("email")
	val email: String
)
