package com.yb.showflix.repository

import com.yb.showflix.model.detail.ShowDetail
import com.yb.showflix.model.search.ShowsResponse
import kotlinx.coroutines.flow.Flow

interface ShowsRepository {
    suspend fun getShowsByTitleResponse(title:String): Flow<ResponseState>
    suspend fun getShowByImdbIdResponse(id:String):Flow<ResponseState>
}