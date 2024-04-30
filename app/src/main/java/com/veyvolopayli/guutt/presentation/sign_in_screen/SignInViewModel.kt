package com.veyvolopayli.guutt.presentation.sign_in_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.domain.usecases.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _signUpState = MutableLiveData<Resource<String>>()
    val signUpState : LiveData<Resource<String>> = _signUpState

    fun signUp(login: String, password: String) {
        signUpUseCase(login, password).onEach { resource ->
            _signUpState.value = resource
        }.launchIn(viewModelScope)
    }
}