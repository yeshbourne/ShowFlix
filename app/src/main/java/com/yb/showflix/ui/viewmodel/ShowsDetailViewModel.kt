package com.yb.showflix.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yb.showflix.model.detail.ShowDetail
import com.yb.showflix.repository.ResponseState
import com.yb.showflix.repository.ShowsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsDetailViewModel @Inject constructor(
    private val showsRepositoryImpl: ShowsRepositoryImpl
) : ViewModel() {

    private var _showsDetail = MutableLiveData<Flow<ResponseState>>()
    val showDetail: LiveData<Flow<ResponseState>>
        get() = _showsDetail

    fun getShowDetail(imdbId: String){
        CoroutineScope(IO).launch {
            val response = showsRepositoryImpl.getShowByImdbIdResponse(imdbId)
            response.collectLatest {
                Log.d("ShowsDetail",it.toString())
            }
            _showsDetail.postValue(response)
        }
    }
}