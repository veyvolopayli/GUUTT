package com.veyvolopayli.guutt.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.veyvolopayli.guutt.common.Converters
import com.veyvolopayli.guutt.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class NotesDatabase : RoomDatabase() {
    abstract val notesDao: NotesDao

    companion object {
        const val DATABASE_NAME = "notes_database"
    }
}