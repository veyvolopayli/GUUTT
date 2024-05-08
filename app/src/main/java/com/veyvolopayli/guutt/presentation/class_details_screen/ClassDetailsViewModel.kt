package com.veyvolopayli.guutt.presentation.class_details_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.guutt.domain.model.ClassObject
import com.veyvolopayli.guutt.domain.model.Note
import com.veyvolopayli.guutt.domain.usecases.notes.GetNoteUseCase
import com.veyvolopayli.guutt.domain.usecases.notes.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassDetailsViewModel @Inject constructor(
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase,
) : ViewModel() {

    private val _noteState = MutableLiveData<Note>()
    val noteState: LiveData<Note> = _noteState

    fun getNote(classObject: ClassObject) {
        viewModelScope.launch {
            getNoteUseCase(classObject)?.let { note: Note ->
                _noteState.value = note
            }
        }
    }

    fun saveNote(classObject: ClassObject, noteText: String) {
        viewModelScope.launch {
            val note = Note(
                classTitle = classObject.title,
                classDateTime = classObject.start,
                content = noteText
            )
            saveNoteUseCase(note)
        }
    }

}