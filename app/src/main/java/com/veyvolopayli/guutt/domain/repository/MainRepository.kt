package com.veyvolopayli.guutt.domain.repository

import com.veyvolopayli.guutt.domain.model.ClassObject
import retrofit2.Response

interface MainRepository {
    suspend fun getGroups(): Response<List<String>>

    suspend fun getClasses(group: String): Map<String, List<ClassObject>>
}