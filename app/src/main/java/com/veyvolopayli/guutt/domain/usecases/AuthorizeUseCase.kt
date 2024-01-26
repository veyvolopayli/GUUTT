package com.veyvolopayli.guutt.domain.usecases

import android.util.Log
import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.domain.repository.AuthRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthorizeUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(cookie: String) = flow<Resource<Unit>> {
        try {
            val classes = authRepository.authorize(cookie)
            emit(Resource.Success(classes))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
            Log.e("Auth", e.message.toString())
        }
    }

}