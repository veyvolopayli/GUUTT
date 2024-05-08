package com.veyvolopayli.guutt.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.veyvolopayli.guutt.common.NoteId
import com.veyvolopayli.guutt.domain.model.Note
import java.time.LocalDateTime

@Dao
interface NotesDao {
    @Query("SELECT * FROM class_notes")
    suspend fun getAllNotes(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note): NoteId

    @Query("SELECT * FROM class_notes WHERE classTitle = :classTitle AND classDateTime = :classDateTime")
    suspend fun getNote(classTitle: String, classDateTime: LocalDateTime): Note?

    @Query("SELECT * FROM class_notes WHERE id = :id")
    suspend fun getNoteById(id: Long): Note?
}