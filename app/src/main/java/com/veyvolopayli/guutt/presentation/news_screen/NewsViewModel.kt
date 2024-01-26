package com.veyvolopayli.guutt.presentation.news_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.domain.model.News
import com.veyvolopayli.guutt.domain.usecases.GetAllNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getAllNewsUseCase: GetAllNewsUseCase
): ViewModel() {
    
    private val _newsState = MutableLiveData<List<News>>()
    val newsState: LiveData<List<News>> = _newsState

    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String> = _toast
    
    init {
        getNews()
    }

    private fun getNews() {
        getAllNewsUseCase().onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    _newsState.value = resource.data ?: emptyList()
                }
                is Resource.Error -> {
                    _toast.value = "Some error"
                    Log.e("NEWS_ERROR", resource.error.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}