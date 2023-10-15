package com.veyvolopayli.guutt.presentation.home_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.guutt.domain.model.Day
import com.veyvolopayli.guutt.domain.model.Day2
import com.veyvolopayli.guutt.domain.model.Lesson
import com.veyvolopayli.guutt.domain.usecases.GetClassesUseCase
import com.veyvolopayli.guutt.domain.usecases.GetDataForAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getDataForAuthUseCase: GetDataForAuthUseCase,
    getClassesUseCase: GetClassesUseCase
) : ViewModel() {
    private val _daysState = MutableLiveData<List<Day2>>()
    val daysState: LiveData<List<Day2>> = _daysState

    init {
        /*val days = listOf<Day>(
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
        )*/

        getDataForAuthUseCase().onEach {

        }.launchIn(viewModelScope)

        getClassesUseCase().onEach {
            val days = it?.map { classes ->
                Day2(
                    date = LocalDate.parse(classes.first().start.take(10)),
                    classes = classes
                )
            }
            if (days != null) {
                _daysState.value = days!!
            } else {
                Log.e("VM", "Error")
            }
        }.launchIn(viewModelScope)
    }
}