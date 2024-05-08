package com.veyvolopayli.guutt.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.veyvolopayli.guutt.common.Converters
import com.veyvolopayli.guutt.domain.model.ClassDBO

@Database(
    entities = [ClassDBO::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ClassesDatabase: RoomDatabase() {
    abstract val classesDao: ClassesDAO

    companion object {
        const val DATABASE_NAME = "classes_database"
    }
}