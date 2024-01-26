package com.veyvolopayli.guutt.domain.repository

import com.veyvolopayli.guutt.domain.model.ClassObject

interface AuthRepository {
    suspend fun authorize(cookie: String)
}