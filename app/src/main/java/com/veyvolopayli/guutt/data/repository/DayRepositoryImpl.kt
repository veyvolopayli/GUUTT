package com.veyvolopayli.guutt.data.repository

import com.veyvolopayli.guutt.data.data_source.DayDAO
import com.veyvolopayli.guutt.domain.model.Day
import com.veyvolopayli.guutt.domain.repository.DayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DayRepositoryImpl(private val dao: DayDAO) : DayRepository {
    override suspend fun insertDay(day: Day) {
        dao.insertDay(day.toParsed())
    }

    override suspend fun getDay(id: String): Day {
        return dao.getDay(id).toDay()
    }

    override suspend fun deleteDay(day: Day) {
        dao.deleteDay(day.toParsed())
    }

    override fun getAllDays(): Flow<List<Day>> {
        return dao.getAllDays().map { parsedDays ->
            parsedDays.map { parsedDay ->
                parsedDay.toDay()
            }
        }
    }
}