package com.veyvolopayli.guutt.data.remote

import com.veyvolopayli.guutt.domain.model.sign_in.SignInRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface GuuApi {

    @GET("auth/login")
    suspend fun getHtmlForAuthorization(@Header("Accept") accept: String = "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"): Response<ResponseBody>

    @POST("auth/login")
    suspend fun signIn(@Body signInRequest: SignInRequest, @Header("Cookie") cookie: String, @Header("Accept") accept: String = "*/*"): Response<ResponseBody>

    @GET("student/classes")
    suspend fun getClasses(@Header("Cookie") cookie: String, @Header("Accept") accept: String = "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"): Response<ResponseBody>

    @GET("site/captcha")
    suspend fun getCaptchaImage(@Query("v") v: String, @Header("Accept") accept: String = "*/*"): Response<ResponseBody>
}