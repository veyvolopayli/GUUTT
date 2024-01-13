package com.veyvolopayli.guutt.presentation.sign_in_screen

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _signInSecurityState = MutableLiveData<SignInSecurityState>(SignInSecurityState())
    val signInSecurityState: LiveData<SignInSecurityState> = _signInSecurityState

    private val _afterSignInData = MutableLiveData<String>()
    val afterSignInData: LiveData<String> = _afterSignInData

    private val _captcha = MutableLiveData<Bitmap>()
    val captcha: LiveData<Bitmap> = _captcha

    private val _isAuthorized = MutableLiveData<Boolean>()
    val isAuthorized: LiveData<Boolean> = _isAuthorized

    fun setSecure(_csrf: String, csrfToken: String, captchaPath: String, cookies: String) {
        _signInSecurityState.value = _signInSecurityState.value?.copy(
            _csrf = _csrf, csrfToken = csrfToken, captchaPath = captchaPath, cookies = cookies
        )
    }
}