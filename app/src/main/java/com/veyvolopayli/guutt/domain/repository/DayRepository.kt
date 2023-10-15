package com.veyvolopayli.guutt.domain.repository

import com.veyvolopayli.guutt.domain.model.Day3
import kotlinx.coroutines.flow.Flow

interface DayRepository {
    suspend fun insertDay(day3: Day3)
    suspend fun getDay(id: String): Day3
    suspend fun deleteDay(day3: Day3)
    fun getAllDays(): Flow<List<Day3>>
}