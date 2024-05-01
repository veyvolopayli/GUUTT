package com.veyvolopayli.guutt.data.repository

import com.veyvolopayli.guutt.data.remote.GuuTtApi
import com.veyvolopayli.guutt.domain.model.ClassObject
import com.veyvolopayli.guutt.domain.repository.MainRepository
import kotlinx.serialization.encoding.Decoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val guuTtApi: GuuTtApi
): MainRepository {
    override suspend fun getGroups(): List<String> {
        return guuTtApi.getGroups()
    }
    override suspend fun getClasses(group: String): Map<LocalDate, List<ClassObject>> {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE
        return guuTtApi.getClasses(group).map {
            LocalDate.parse(it.key, formatter) to it.value
        }.toMap()
    }
}