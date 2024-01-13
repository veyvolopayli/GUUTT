package com.veyvolopayli.guutt.presentation.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.domain.model.ClassObject
import com.veyvolopayli.guutt.domain.model.Day
import com.veyvolopayli.guutt.domain.usecases.GetClassesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getClassesUseCase: GetClassesUseCase
) : ViewModel() {

    private val _daysState = MutableLiveData<List<Day>>()
    val daysState: LiveData<List<Day>> = _daysState

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    fun getClasses(group: String) {
        getClassesUseCase(group).onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    val classes = resource.data ?: emptyMap<String, List<ClassObject>>()
                    val mappedClasses = classes.map {
                        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val date = LocalDate.parse(it.key, format)
                        Day(date = date, classes = it.value)
                    }
                    _daysState.value = mappedClasses
                }
                is Resource.Error -> {
                    _toastMessage.value = resource.error ?: ""
                }
            }
        }.launchIn(viewModelScope)
    }
}