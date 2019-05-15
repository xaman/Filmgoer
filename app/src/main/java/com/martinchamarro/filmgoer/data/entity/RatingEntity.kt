package com.martinchamarro.filmgoer.data.entity

import com.google.gson.annotations.SerializedName

data class RatingEntity(
        @SerializedName("Source") val source: String?,
        @SerializedName("Value") val value: String?)