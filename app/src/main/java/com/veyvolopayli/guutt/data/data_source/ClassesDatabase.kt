package com.veyvolopayli.guutt.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.veyvolopayli.guutt.domain.model.ClassObjectWithNote
import com.veyvolopayli.guutt.domain.model.ParsedDay

@Database(
    entities = [ClassObjectWithNote::class],
    version = 1
)
abstract class ClassesDatabase: RoomDatabase() {
    abstract val classesDao: ClassesDAO

    companion object {
        const val DATABASE_NAME = "classes_database"
    }
}