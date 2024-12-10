package com.example.nextstep.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CVData(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val linkedin: String,
    val schoolOne: String,
    val about: String,
    val graduatedOne: String,
    val degreeOne: String,
    val schoolTwo: String,
    val graduatedTwo: String,
    val degreeTwo: String,
    val schoolThree: String,
    val graduatedThree: String,
    val degreeThree: String,
    val skillOne: String,
    val skillTwo: String,
    val skillThree: String,
    val skillFour: String,
    val skillFive: String,
    val experienceOne: String,
    val experienceTwo: String,
    val experienceThree: String,
    val experienceDetailOne: String,
    val experienceDetailTwo: String,
    val experienceDetailThree: String,
    val projectOne: String,
    val projectTwo: String,
    val projectThree: String,
    val projectDetailOne: String,
    val projectDetailTwo: String,
    val projectDetailThree: String,
) : Parcelable
