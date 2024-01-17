package com.veyvolopayli.guutt.domain.usecases

import android.util.Log
import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import javax.inject.Inject

class FetchGroupsUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(): Flow<Resource<List<String>>> = flow {
        try {
            val groups = repository.getGroups()
            emit(Resource.Success(groups))
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    when(e.code()) {
                        409 -> emit(Resource.Error("Server side error"))
                    }
                }
                is ConnectException -> {
                    Log.e("CONNECTION EXCEPTION", e.message.toString())
                    emit(Resource.Error("Проверьте интернет соединение"))
                }
                is IOException -> {
                    Log.e("IOException", e.message.toString())
                    emit(Resource.Error("Проверьте интернет соединение"))
                }
            }
        }
    }
}