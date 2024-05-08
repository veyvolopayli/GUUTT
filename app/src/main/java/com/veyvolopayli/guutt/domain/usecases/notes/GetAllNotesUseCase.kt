package com.veyvolopayli.guutt.domain.usecases.notes

import com.veyvolopayli.guutt.domain.model.Note
import com.veyvolopayli.guutt.domain.repository.ClassNotesRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val classNotesRepository: ClassNotesRepository
) {
    suspend operator fun invoke(): List<Note> {
        return classNotesRepository.getAllNotes()
    }
}