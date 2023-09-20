package com.veyvolopayli.guutt.presentation.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.veyvolopayli.guutt.domain.model.Day
import com.veyvolopayli.guutt.domain.model.Lesson

//@HiltViewModel
class HomeViewModel : ViewModel() {
    private val _daysState = MutableLiveData<List<Day>>()
    val daysState: LiveData<List<Day>> = _daysState

    init {
        val days = listOf<Day>(
            Day(dayOfWeek = "Четверг", lessons = listOf(
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", professorFullName = "Алексей Глазков", week = 4),
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", professorFullName = "Алексей Глазков", week = 4),
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", professorFullName = "Алексей Глазков", week = 4)
            ), weekPosition = 0, weekType = 1),
            Day(dayOfWeek = "Четверг", lessons = listOf(
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", professorFullName = "Алексей Глазков", week = 4),
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", professorFullName = "Алексей Глазков", week = 4),
            ), weekPosition = 0, weekType = 1),
            Day(dayOfWeek = "Четверг", lessons = listOf(
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", professorFullName = "Алексей Глазков", week = 4),
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", professorFullName = "Алексей Глазков", week = 4),
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", professorFullName = "Алексей Глазков", week = 4),
                Lesson(type = "ЛЗ", lessonTime = "11:35-13:05", lessonName = "Программная инженерия", classroom = "ЛК-409", professorFullName = "Алексей Глазков", week = 4)
            ), weekPosition = 0, weekType = 1)
        )

        _daysState.value = days
    }
}