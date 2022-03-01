package com.yb.showflix.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yb.showflix.repository.ResponseState
import com.yb.showflix.repository.ShowsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel @Inject constructor(
    private val showsRepositoryImpl: ShowsRepositoryImpl
) : ViewModel() {

    private var _shows = MutableLiveData<Flow<ResponseState>>()
    val shows: LiveData<Flow<ResponseState>>
        get() = _shows

    fun getShows(search: String) {
        CoroutineScope(IO).launch {
            _shows.postValue(showsRepositoryImpl.getShowsByTitleResponse(search))
        }
    }
}