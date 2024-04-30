package com.veyvolopayli.guutt.domain.repository

interface AuthRepository {
    suspend fun signUp(login: String, password: String): String
}