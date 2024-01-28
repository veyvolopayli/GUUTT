package com.veyvolopayli.guutt.data.repository

import com.veyvolopayli.guutt.data.data_source.ClassesDAO
import com.veyvolopayli.guutt.domain.model.Day3
import com.veyvolopayli.guutt.domain.repository.DayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DayRepositoryImpl(private val dao: ClassesDAO) : DayRepository {
    override suspend fun insertDay(day3: Day3) {
        dao.insertDay(day3.toParsed())
    }

    override suspend fun getDay(id: String): Day3 {
        return dao.getDay(id).toDay()
    }

    override suspend fun deleteDay(day3: Day3) {
        dao.deleteDay(day3.toParsed())
    }

    override fun getAllDays(): Flow<List<Day3>> {
        return dao.getGroupClasses().map { parsedDays ->
            parsedDays.map { parsedDay ->
                parsedDay.toDay()
            }
        }
    }
}