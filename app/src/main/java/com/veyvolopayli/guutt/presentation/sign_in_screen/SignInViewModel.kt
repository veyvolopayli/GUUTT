package com.veyvolopayli.guutt.presentation.sign_in_screen

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.guutt.domain.usecases.GetCaptchaImageUseCase
import com.veyvolopayli.guutt.domain.usecases.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val getCaptchaImageUseCase: GetCaptchaImageUseCase
) : ViewModel() {

    private val _signInSecurityState = MutableLiveData<SignInSecurityState>(SignInSecurityState())
    val signInSecurityState: LiveData<SignInSecurityState> = _signInSecurityState

    private val _afterSignInData = MutableLiveData<String>()
    val afterSignInData: LiveData<String> = _afterSignInData

    private val _captcha = MutableLiveData<Bitmap>()
    val captcha: LiveData<Bitmap> = _captcha

    fun setSecure(_csrf: String, csrfToken: String, captchaPath: String, cookies: String) {
        _signInSecurityState.value = _signInSecurityState.value?.copy(
            _csrf = _csrf, csrfToken = csrfToken, captchaPath = captchaPath, cookies = cookies
        )
    }

    fun signIn(login: String, password: String, captcha: String) {
        signInUseCase(
            login = login,
            password = password,
            captcha = captcha,
            _csrf = _signInSecurityState.value?._csrf ?: "",
            csrfToken = _signInSecurityState.value?.csrfToken ?: "",
            cookie = _signInSecurityState.value?.cookies ?: ""
        ).onEach {
            _afterSignInData.value = it
        }.launchIn(viewModelScope)

        println(signInSecurityState.value)
    }

    fun getCaptchaImage(v: String) {
        getCaptchaImageUseCase(v).onEach { bitmap ->
            bitmap?.let {
                _captcha.value = it.image
                _signInSecurityState.value = _signInSecurityState.value?.copy(
                    cookies = it.cookie
                )
            }
        }.launchIn(viewModelScope)
    }

}