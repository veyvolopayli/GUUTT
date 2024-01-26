package com.veyvolopayli.guutt.presentation.sign_in_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.domain.repository.AuthRepository
import com.veyvolopayli.guutt.domain.usecases.AuthorizeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authorizeUseCase: AuthorizeUseCase
) : ViewModel() {

    private val _webPageResult = MutableLiveData<WebPageResult>()
    val webPageResult: LiveData<WebPageResult> = _webPageResult

    private val _authResult = MutableLiveData<Boolean>()
    val authResult: LiveData<Boolean> = _authResult

    fun setWebPageResult(pageResult: WebPageResult) {
        _webPageResult.value = pageResult
    }

    fun authorize(cookie: String) {
        authorizeUseCase(cookie).onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    _authResult.value = true
                }
                is Resource.Error -> {
                    _authResult.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

}