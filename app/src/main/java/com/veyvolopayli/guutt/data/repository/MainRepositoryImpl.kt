package com.veyvolopayli.guutt.data.repository

import com.veyvolopayli.guutt.data.remote.GuuTtApi
import com.veyvolopayli.guutt.domain.model.ClassObject
import com.veyvolopayli.guutt.domain.repository.MainRepository
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val guuTtApi: GuuTtApi
): MainRepository {
    override suspend fun getGroups(): List<String> {
        return guuTtApi.getGroups()
    }

    override suspend fun getClasses(group: String): Map<String, List<ClassObject>> {
        return guuTtApi.getClasses(group)
    }
}