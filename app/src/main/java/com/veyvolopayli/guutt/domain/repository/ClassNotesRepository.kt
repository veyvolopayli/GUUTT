package com.veyvolopayli.guutt.domain.repository

import com.veyvolopayli.guutt.domain.model.Note
import java.time.LocalDateTime

interface ClassNotesRepository {
    suspend fun getAllNotes(): List<Note>

    suspend fun insertNote(note: Note)

    suspend fun getNote(classTitle: String, classDateTime: LocalDateTime): Note?

    suspend fun getNoteById(id: Long): Note?
}