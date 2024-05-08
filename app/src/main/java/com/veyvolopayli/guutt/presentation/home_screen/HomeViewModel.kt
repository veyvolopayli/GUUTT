package com.veyvolopayli.guutt.presentation.home_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.domain.model.ClassObject
import com.veyvolopayli.guutt.domain.model.Day
import com.veyvolopayli.guutt.domain.model.Note
import com.veyvolopayli.guutt.domain.usecases.GetClassesFromDbUseCase
import com.veyvolopayli.guutt.domain.usecases.GetClassesUseCase
import com.veyvolopayli.guutt.domain.usecases.GetCurrentGroupUseCase
import com.veyvolopayli.guutt.domain.usecases.notes.GetNoteUseCase
import com.veyvolopayli.guutt.domain.usecases.notes.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getClassesUseCase: GetClassesUseCase,
    private val getCurrentGroupUseCase: GetCurrentGroupUseCase,
    private val getClassesFromDbUseCase: GetClassesFromDbUseCase,
) : ViewModel() {

    private val _daysState = MutableLiveData<List<Day>>()
    val daysState: LiveData<List<Day>> = _daysState

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    private val _groupState = MutableLiveData<String>()
    val groupState: LiveData<String> = _groupState

    private val _currentItem = MutableLiveData<Int>()
    val currentItem : LiveData<Int> = _currentItem

    init {
        getCurrentGroup()
    }

    fun setCurrentVpItem(position: Int) {
        _currentItem.value = position
    }

    private fun getClasses(group: String) {
        getClassesUseCase(group).onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    val classes = resource.data
                    val days = classes.map {
                        Day(date = it.key, classes = it.value)
                    }.drop(classes.size / 2)
                    _daysState.value = days
                    setToday(days)
                }
                is Resource.Error -> {
                    _toastMessage.value = resource.error
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getClassesFromDb(group: String): Flow<List<ClassObject>> {
        return getClassesFromDbUseCase(group)
    }

    private fun setToday(days: List<Day>) {
        val today = LocalDate.now()
        val currentDayPosition = days.indexOfFirst { it.date == today }
        setCurrentVpItem(currentDayPosition)
    }

    private fun getCurrentGroup() {
        getCurrentGroupUseCase().onEach { group ->
            group?.let {
                Log.e("GROUP", it)
                _groupState.value = it
                val classesFromDbFlow = getClassesFromDb(group)
                if (classesFromDbFlow.collect())
                getClasses(it)
            }
        }.launchIn(viewModelScope)
    }
}