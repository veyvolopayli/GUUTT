package com.veyvolopayli.guutt.domain.repository

import com.veyvolopayli.guutt.domain.model.ClassObject
import com.veyvolopayli.guutt.domain.model.ClassDBO
import kotlinx.coroutines.flow.Flow

interface DbClassesRepository {
    suspend fun getGroupClasses(group: String): Flow<List<ClassDBO>>
    suspend fun insertClasses(group: String, classes: List<ClassObject>)
}