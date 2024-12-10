package com.example.nextstep.data.model

import com.google.gson.annotations.SerializedName

data class PredictedJobResponse(

	@field:SerializedName("skills")
	val skills: List<String>,

	@field:SerializedName("request_id")
	val requestId: String,

	@field:SerializedName("predicted_jobs")
	val predictedJobs: List<PredictedJobsItem>
)

data class PredictedJobsItem(

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("roadmapId")
	val roadmapId: String
)
