package com.yb.showflix.model.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Show(
    @Json(name = "Poster") val Poster: String, // https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_SX300.jpg
    @Json(name = "Title") val Title: String, // The Lord of the Rings: The Fellowship of the Ring
    @Json(name = "Type") val Type: String, // movie
    @Json(name = "Year") val Year: String, // 2001
    @Json(name = "imdbID") val imdbID: String // tt0120737
)
