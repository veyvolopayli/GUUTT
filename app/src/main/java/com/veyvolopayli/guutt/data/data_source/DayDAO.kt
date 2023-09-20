package com.veyvolopayli.guutt.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.veyvolopayli.guutt.domain.model.Day
import com.veyvolopayli.guutt.domain.model.ParsedDay
import kotlinx.coroutines.flow.Flow

@Dao
interface DayDAO {

    @Upsert
    suspend fun insertDay(day: ParsedDay)

    @Query("SELECT * FROM parsedday WHERE id = :id")
    suspend fun getDay(id: String): ParsedDay

    @Query("SELECT * FROM parsedday")
    fun getAllDays(): Flow<List<ParsedDay>>

    @Delete
    suspend fun deleteDay(day: ParsedDay)

}