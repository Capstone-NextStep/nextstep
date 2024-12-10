package com.example.nextstep.data.model

import com.google.gson.annotations.SerializedName

data class CreatedAt(

    @field:SerializedName("_nanoseconds")
    val nanoseconds: Int,

    @field:SerializedName("_seconds")
    val seconds: Int
)
