package com.yb.showflix.model.detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowDetail(
    @Json(name = "Actors") val Actors: String, // Elijah Wood, Ian McKellen, Orlando Bloom
    @Json(name = "Awards") val Awards: String, // Won 4 Oscars. 121 wins & 126 nominations total
    @Json(name = "BoxOffice") val BoxOffice: String, // $315,710,750
    @Json(name = "Country") val Country: String, // New Zealand, United States
    @Json(name = "DVD") val DVD: String, // 06 Aug 2002
    @Json(name = "Director") val Director: String, // Peter Jackson
    @Json(name = "Genre") val Genre: String, // Action, Adventure, Drama
    @Json(name = "Language") val Language: String, // English, Sindarin
    @Json(name = "Metascore") val Metascore: String, // 92
    @Json(name = "Plot") val Plot: String, // A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.
    @Json(name = "Poster") val Poster: String, // https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_SX300.jpg
    @Json(name = "Production") val Production: String, // N/A
    @Json(name = "Rated") val Rated: String, // PG-13
    @Json(name = "Ratings") val Ratings: List<ShowRating>,
    @Json(name = "Released") val Released: String, // 19 Dec 2001
    @Json(name = "Response") val Response: String, // True
    @Json(name = "Runtime") val Runtime: String, // 178 min
    @Json(name = "Title") val Title: String, // The Lord of the Rings: The Fellowship of the Ring
    @Json(name = "Type") val Type: String, // movie
    @Json(name = "Website") val Website: String, // N/A
    @Json(name = "Writer") val Writer: String, // J.R.R. Tolkien, Fran Walsh, Philippa Boyens
    @Json(name = "Year") val Year: String, // 2001
    @Json(name = "imdbID") val imdbID: String, // tt0120737
    @Json(name = "imdbRating") val imdbRating: String, // 8.8
    @Json(name = "imdbVotes") val imdbVotes: String // 1,767,565
)