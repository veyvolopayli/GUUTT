package com.veyvolopayli.guutt.data.repository

import com.veyvolopayli.guutt.data.data_source.NotesDao
import com.veyvolopayli.guutt.domain.model.Note
import com.veyvolopayli.guutt.domain.repository.ClassNotesRepository
import java.time.LocalDateTime
import javax.inject.Inject

class ClassNotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
) : ClassNotesRepository {
    override suspend fun getAllNotes(): List<Note> {
        return notesDao.getAllNotes()
    }

    override suspend fun insertNote(note: Note) {
        return notesDao.insertNote(note)
    }

    override suspend fun getNote(classTitle: String, classDateTime: LocalDateTime): Note? {
        return notesDao.getNote(classTitle, classDateTime)
    }

    override suspend fun getNoteById(id: Long): Note? {
        return notesDao.getNoteById(id)
    }
}