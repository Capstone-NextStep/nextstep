package com.example.nextstep.data.model

import com.google.gson.annotations.SerializedName

data class TemplateResponse(

	@field:SerializedName("resume")
	val resume: String,

	@field:SerializedName("career")
	val career: String,

	@field:SerializedName("request_id")
	val requestId: String
)
