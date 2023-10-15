package com.veyvolopayli.guutt.domain.repository

import com.veyvolopayli.guutt.domain.model.sign_in.SignInRequest
import okhttp3.ResponseBody
import retrofit2.Response

interface MainRepository {

    suspend fun getDataForAuthorization(): Response<ResponseBody>

    suspend fun signIn(signInRequest: SignInRequest, cookie: String): Response<ResponseBody>

    suspend fun getClasses(cookie: String): Response<ResponseBody>

    suspend fun getCaptchaImage(v: String): Response<ResponseBody>

}