package com.veyvolopayli.guutt.domain.usecases.notes

import com.veyvolopayli.guutt.domain.model.ClassObject
import com.veyvolopayli.guutt.domain.model.Note
import com.veyvolopayli.guutt.domain.repository.ClassNotesRepository
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val classNotesRepository: ClassNotesRepository
) {
    suspend operator fun invoke(classObject: ClassObject): Note? {
        return classNotesRepository.getNote(classObject.title, classObject.start)
    }

    suspend operator fun invoke(noteId: Long): Note? {
        return classNotesRepository.getNoteById(noteId)
    }
}