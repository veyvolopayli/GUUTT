package com.veyvolopayli.guutt.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.veyvolopayli.guutt.domain.model.ParsedDay

@Database(
    entities = [ParsedDay::class],
    version = 1
)
abstract class DayDatabase: RoomDatabase() {
    abstract val dayDao: DayDAO

    companion object {
        const val DATABASE_NAME = "day_database"
    }
}