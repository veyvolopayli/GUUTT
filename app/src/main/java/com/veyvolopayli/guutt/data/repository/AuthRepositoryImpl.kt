package com.veyvolopayli.guutt.data.repository

import com.veyvolopayli.guutt.data.model.SignUpRequest
import com.veyvolopayli.guutt.data.remote.GuuTtApi
import com.veyvolopayli.guutt.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val guuTtApi: GuuTtApi
) : AuthRepository {
    override suspend fun signUp(login: String, password: String): String {
        return guuTtApi.signUp(SignUpRequest(login, password))
    }
}