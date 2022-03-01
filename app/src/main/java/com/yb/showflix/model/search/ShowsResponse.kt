package com.yb.showflix.model.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowsResponse(
    @Json(name = "Response") val Response: String, // True
    @Json(name = "Search") val Search: List<Show>,
    @Json(name = "totalResults") val totalResults: String // 67
)