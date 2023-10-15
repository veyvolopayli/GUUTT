package com.veyvolopayli.guutt.domain.usecases

import android.util.Log
import com.google.gson.Gson
import com.veyvolopayli.guutt.domain.model.sign_in.LoginData
import com.veyvolopayli.guutt.domain.model.sign_in.SignInRequest
import com.veyvolopayli.guutt.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: MainRepository
) {

    operator fun invoke(_csrf: String, csrfToken: String, login: String, password: String, captcha: String, cookie: String): Flow<String?> = flow {
        try {
            val signInRequest = SignInRequest(
                _csrf = "hfBBoOtg2TlegrCNotHRlIicmRtaND5wstpugn1hPq60nxLhvBWBfx_d4cvomb7F7PXoSm51VT-DkQL7Chdpyw==",
                csrfToken = "178e1186434faad302ec8dba32437041420d92c45fa3bc7142bc7c803796fb7b6480db7727b75167",
                siteLogin = LoginData(captcha = "vfdoyt", login = login, password = password)
            )

            val json = Gson().toJson(signInRequest)
            Log.e("JSON", json)

            val response = repository.signIn(signInRequest = signInRequest, "PHPSESSID=jvdlorvsmb3b1l17rvd8cce1acpfqs2m; _csrf=9e86c71ceb27662771b99948388b874f547ea552a6de2c76579705f2ea4df310a%3A2%3A%7Bi%3A0%3Bs%3A5%3A%22_csrf%22%3Bi%3A1%3Bs%3A32%3A%22ge25BQFyxFkiPPG2OsBpUj5VSDVSepo0%22%3B%7D; session-cookie=178e12ab1fe5f9413863fc6d18991a246a0f81e63ca861afcbb9e30d551d51ba4003a5643641fa0b24249420da3ca10d")
            if (response.isSuccessful) {
                val htmlData = response.body()?.string()
                if (htmlData != null && htmlData.contains("Глазков")) {
                    Log.e("Glazkov", "Глазков существует! Авторизация успешна!")
                } else {
                    Log.e("Glazkov", "Глазков НЕ существует!")
                }

                Log.e("Code", response.code().toString())

                val responseHeaders = response.headers()
                Log.e("responseHeaders", responseHeaders.toString())
                Log.e("TAG", htmlData ?: "")
            } else {
                println(response.body()?.string())
                Log.e("CODE", response.code().toString())
                throw Exception("Is not successful")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(null)
        }
    }

}