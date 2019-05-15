package com.martinchamarro.filmgoer.data.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RatingEntity(
        @SerializedName("Source") val source: String?,
        @SerializedName("Value") val value: String?)