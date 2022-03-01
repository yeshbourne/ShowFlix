package com.yb.showflix.repository

import android.util.Log
import com.yb.showflix.model.detail.ShowDetail
import com.yb.showflix.model.search.ShowsResponse
import com.yb.showflix.service.ShowsService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

@Module
@InstallIn(ActivityRetainedComponent::class)
class ShowsRepositoryImpl @Inject constructor(
    private val showsService: ShowsService
) : ShowsRepository {
    override suspend fun getShowsByTitleResponse(title: String): Flow<ResponseState> {
        return getFlowResponse {
            showsService.fetchShowsByTitle(title)
        }
    }

    override suspend fun getShowByImdbIdResponse(id: String): Flow<ResponseState> {
        return getFlowResponse {
            showsService.fetchShowByImdbId(id)
        }
    }

    private suspend fun <T> getFlowResponse(call: suspend () -> Response<T>): Flow<ResponseState> {
        return flow {
            val response = call()
            if (response.isSuccessful) {
                val result = response.body()
                result?.let { result ->
                    emit(ResponseState.Success(result))
                } ?: emit(ResponseState.Error("1005", "data is null"))
            } else {
                emit(ResponseState.Error("1003", "Api call failed"))
            }

        }.catch { e ->
            emit(ResponseState.Error("${e.localizedMessage}", "${e.message}"))
        }
    }
}

sealed class ResponseState {
    data class Success(
        val data: Any
    ) : ResponseState()

    data class Error(
        val errorCode: String,
        val message: String
    ) : ResponseState()
}