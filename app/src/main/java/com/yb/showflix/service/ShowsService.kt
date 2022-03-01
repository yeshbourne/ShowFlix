package com.yb.showflix.service

import com.yb.showflix.BuildConfig
import com.yb.showflix.model.search.ShowsResponse
import com.yb.showflix.model.detail.ShowDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShowsService {
    @GET("?")
    suspend fun fetchShowsByTitle(
        @Query("s") title: String,
        @Query( "apikey") apikey: String = BuildConfig.API_KEY
    ): Response<ShowsResponse>

    @GET("?")
    suspend fun fetchShowByImdbId(
        @Query("i") imdb: String,
        @Query( "apikey") apikey: String = BuildConfig.API_KEY
    ): Response<ShowDetail>
}