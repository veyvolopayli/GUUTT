package com.veyvolopayli.guutt.domain.usecases

import com.veyvolopayli.guutt.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDataForAuthUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(): Flow<String?> = flow {
        try {

            /*val response = repository.getDataForAuthorization()
            if (response.isSuccessful) {
                val htmlResponse = response.body()!!
                val data = htmlResponse.string()
                val doc: Document = Jsoup.parse(data)
                val csrf = doc.select("input[name=_csrf]").attr("value")
                val csrftoken = doc.select("input[name=csrftoken]").attr("value")

                Log.e("FIRST TOKEN", csrf)
                Log.e("SECOND TOKEN", csrftoken)

                Log.e("FULL", data)



            } else {
                throw Exception("Ooooops")
            }*/


//            withContext(Dispatchers.IO) {
//                val doc = Jsoup.connect("https://my.guu.ru/auth/login").header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7").get()
//                val csrf = doc.select("input[name=_csrf]").attr("value")
//                val csrftoken = doc.select("input[name=csrftoken]").attr("value")
//
//                Log.e("FIRST TOKEN", csrf)
//                Log.e("SECOND TOKEN", csrftoken)
//
//                Log.e("FULL", doc.html())
//            }

        } catch (e: Exception) {
            e.printStackTrace()
            emit(null)
        }
    }

}