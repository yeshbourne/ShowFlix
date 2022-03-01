package com.yb.showflix.model.detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowRating(
    @Json(name = "Source") val Source: String, // Internet Movie Database
    @Json(name = "Value") val Value: String // 8.8/10
)
