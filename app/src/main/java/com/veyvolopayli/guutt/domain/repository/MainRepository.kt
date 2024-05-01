package com.veyvolopayli.guutt.domain.repository

import com.veyvolopayli.guutt.domain.model.ClassObject
import retrofit2.Response
import java.time.LocalDate

interface MainRepository {
    suspend fun getGroups(): List<String>

    suspend fun getClasses(group: String): Map<LocalDate, List<ClassObject>>
}