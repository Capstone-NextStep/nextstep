package com.example.nextstep.data.model

import com.google.gson.annotations.SerializedName

data class RoadmapsResponse(

	@field:SerializedName("roadmaps")
	val roadmaps: List<RoadmapsItem>
)


data class RoadmapsItem(

	@field:SerializedName("createdAt")
	val createdAt: CreatedAt,

	@field:SerializedName("career")
	val career: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("steps")
	val steps: List<String>
)
