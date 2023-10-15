package com.veyvolopayli.guutt.domain.usecases

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.veyvolopayli.guutt.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetCaptchaImageUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(v: String): Flow<CaptchaAndCookie?> = flow {
        try {
            val response = repository.getCaptchaImage(v)
            val byteStream = response.body()?.byteStream() ?: throw Exception("Response body is empty")
            val bitmap = BitmapFactory.decodeStream(byteStream)

            val responseHeaders = response.headers()
            val awfulCookieValues = responseHeaders.values("Set-Cookie")
            val normalCookieValues = arrayListOf<String>()
            awfulCookieValues.forEach {
                val value = it.split(";").first()
                normalCookieValues.add(value)
            }
            val cookie = normalCookieValues.joinToString("; ")

            Log.e("Cookie", cookie)

//            Log.e("CaptchaHeaders", responseCookie.toString())

            emit(CaptchaAndCookie(bitmap, cookie))
            Log.e("Bitmap", "Bitmap is ok")
        } catch (e: Exception) {
            emit(null)
            e.printStackTrace()
        }
    }

    data class CaptchaAndCookie(
        val image: Bitmap,
        val cookie: String
    )
}