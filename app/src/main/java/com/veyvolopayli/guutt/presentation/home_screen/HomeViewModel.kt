package com.veyvolopayli.guutt.presentation.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.veyvolopayli.guutt.domain.model.Day
import com.veyvolopayli.guutt.domain.model.Lesson
import dagger.hilt.android.lifecycle.HiltViewModel

//@HiltViewModel
class HomeViewModel : ViewModel() {
    private val _daysState = MutableLiveData<List<Day>>()
    val daysState: LiveData<List<Day>> = _daysState

    init {
        val days = listOf<Day>(
            Day(date = "01.10.2023", dayOfWeek = "Четверг", lessons = listOf(
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", lessonProfessorFullName = "Алексей Глазков", week = 4),
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", lessonProfessorFullName = "Алексей Глазков", week = 4),
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", lessonProfessorFullName = "Алексей Глазков", week = 4)
            )),
            Day(date = "01.10.2023", dayOfWeek = "Четверг", lessons = listOf(
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", lessonProfessorFullName = "Алексей Глазков", week = 4),
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", lessonProfessorFullName = "Алексей Глазков", week = 4),
            )),
            Day(date = "01.10.2023", dayOfWeek = "Четверг", lessons = listOf(
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", lessonProfessorFullName = "Алексей Глазков", week = 4),
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", lessonProfessorFullName = "Алексей Глазков", week = 4),
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", lessonProfessorFullName = "Алексей Глазков", week = 4),
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", lessonProfessorFullName = "Алексей Глазков", week = 4)
            ))
        )

        _daysState.value = days
    }
}