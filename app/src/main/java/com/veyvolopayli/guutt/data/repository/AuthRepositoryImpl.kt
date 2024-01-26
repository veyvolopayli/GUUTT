package com.veyvolopayli.guutt.data.repository

import com.veyvolopayli.guutt.data.remote.GuuTtApi
import com.veyvolopayli.guutt.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val guuTtApi: GuuTtApi
) : AuthRepository {
    override suspend fun authorize(cookie: String) {
        return guuTtApi.authorize(cookie)
    }
}