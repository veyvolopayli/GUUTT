package com.veyvolopayli.guutt.data.repository

import com.veyvolopayli.guutt.data.remote.GuuApi
import com.veyvolopayli.guutt.domain.model.sign_in.SignInRequest
import com.veyvolopayli.guutt.domain.repository.MainRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: GuuApi
): MainRepository {
    override suspend fun getDataForAuthorization(): Response<ResponseBody> {
        return api.getHtmlForAuthorization()
    }

    override suspend fun signIn(signInRequest: SignInRequest, cookie: String): Response<ResponseBody> {
        return api.signIn(signInRequest, cookie)
    }

    override suspend fun getClasses(cookie: String): Response<ResponseBody> {
        return api.getClasses(cookie)
    }

    override suspend fun getCaptchaImage(v: String): Response<ResponseBody> {
        return api.getCaptchaImage(v)
    }

}