package com.veyvolopayli.guutt.domain.usecases.notes

import com.veyvolopayli.guutt.common.NoteId
import com.veyvolopayli.guutt.domain.model.Note
import com.veyvolopayli.guutt.domain.repository.ClassNotesRepository
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(
    private val classNotesRepository: ClassNotesRepository
) {
    suspend operator fun invoke(note: Note): NoteId {
        return classNotesRepository.insertNote(note)
    }
}