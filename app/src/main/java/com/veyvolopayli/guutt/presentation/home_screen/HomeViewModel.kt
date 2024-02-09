package com.veyvolopayli.guutt.presentation.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.domain.model.ClassObject
import com.veyvolopayli.guutt.domain.model.Day
import com.veyvolopayli.guutt.domain.usecases.GetClassesUseCase
import com.veyvolopayli.guutt.domain.usecases.GetCurrentGroupUseCase
import com.veyvolopayli.guutt.domain.usecases.SetCurrentGroupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getClassesUseCase: GetClassesUseCase,
    private val setCurrentGroupUseCase: SetCurrentGroupUseCase,
    private val getCurrentGroupUseCase: GetCurrentGroupUseCase
) : ViewModel() {

    private val _daysState = MutableLiveData<List<Day>>()
    val daysState: LiveData<List<Day>> = _daysState

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    private val _groupState = MutableLiveData<String>()
    val groupState: LiveData<String> = _groupState

    private val _currentDayDate = MutableLiveData<String>()
    val currentDayDate: LiveData<String> = _currentDayDate

    private val _classesDates = MutableLiveData<Map<LocalDate, String>>()
    val classesDates: LiveData<Map<LocalDate, String>> = _classesDates

    var somePosition = 0

//    private val _currentViewPagerPosition = MutableLiveData<Long>()
//    val currentViewPagerPosition: LiveData<Long> = _currentViewPagerPosition

    init {
        getCurrentGroup()
    }

//    private fun setTodayViewPagerPosition(days: List<Day>) {
//        val today = LocalDate.now()
//        val todayPosition = days.indexOfFirst { it.date.isEqual(today) }
//        _currentViewPagerPosition
//    }
//
    fun updateCurrentViewPagerPosition(position: Int) {
        somePosition = position
    }

    private fun getClasses(group: String) {
        getClassesUseCase(group).onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    val classes = resource.data ?: emptyMap<String, List<ClassObject>>()
                    val datesMap = mutableMapOf<LocalDate, String>()
                    val mappedClasses = classes.map {
                        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val date = LocalDate.parse(it.key, format)
                        val outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyyг.", Locale("ru"))
                        datesMap[date] = date.format(outputFormatter)
                        Day(date = date, classes = it.value)
                    }
                    _classesDates.value = datesMap
//                    setTodayViewPagerPosition(mappedClasses)
                    _daysState.value = mappedClasses
//                    setCurrentGroupUseCase(newGroup = group)
                }
                is Resource.Error -> {
                    _toastMessage.value = resource.error ?: ""
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCurrentGroup() {
        getCurrentGroupUseCase().onEach { group ->
            group?.let {
                _groupState.value = it
                getClasses(it)
            }
        }.launchIn(viewModelScope)
    }
}