package com.veyvolopayli.guutt.domain.usecases

import android.util.Log
import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.domain.model.ClassObject
import com.veyvolopayli.guutt.domain.repository.MainRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.time.LocalDate
import javax.inject.Inject

class GetClassesUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(group: String) = flow<Resource<Map<LocalDate, List<ClassObject>>>> {
        try {
            val classes = repository.getClasses(group)
            emit(Resource.Success(classes.toList().drop(classes.size / 2).toMap()))
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("ERROR", e.message.toString())
            when (e) {
                is HttpException -> {
                    when(e.code()) {
                        409 -> emit(Resource.Error("Server side exception."))
                    }
                }
                is IOException -> {
                    emit(Resource.Error("Local exception."))
                }
            }
        }
    }
}