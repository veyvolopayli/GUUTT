package com.veyvolopayli.guutt.domain.usecases

import android.util.Log
import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.domain.repository.AuthRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(login: String, password: String) = flow {
        try {
            val signUpMessage = authRepository.signUp(login = login, password = password)
            emit(Resource.Success(signUpMessage))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error(e.response()?.errorBody()?.string() ?: ""))
        }
    }
}