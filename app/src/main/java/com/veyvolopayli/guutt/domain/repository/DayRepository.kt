package com.veyvolopayli.guutt.domain.repository

import com.veyvolopayli.guutt.domain.model.Day
import kotlinx.coroutines.flow.Flow

interface DayRepository {
    suspend fun insertDay(day: Day)
    suspend fun getDay(id: String): Day
    suspend fun deleteDay(day: Day)
    fun getAllDays(): Flow<List<Day>>
}