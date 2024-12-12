package com.example.nextstep.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PredictedJobResponse(

	@field:SerializedName("skills")
	val skills: List<String>,

	@field:SerializedName("request_id")
	val requestId: String,

	@field:SerializedName("predicted_jobs")
	val predictedJobs: List<PredictedJobsItem>
)

@Parcelize
data class PredictedJobsItem(

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("roadmapId")
	val roadmapId: String
) : Parcelable
