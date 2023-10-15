package com.veyvolopayli.guutt.presentation.home_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.guutt.domain.model.Day
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
    private val _daysState = MutableLiveData<List<Day>>()
    val daysState: LiveData<List<Day>> = _daysState

    init {
        /*getDataForAuthUseCase().onEach {

        }.launchIn(viewModelScope)*/

        getClassesUseCase().onEach {
            val days = it
            if (days != null) {
                _daysState.value = days!!
            } else {
                Log.e("VM", "Error")
            }
        }.launchIn(viewModelScope)
    }
}