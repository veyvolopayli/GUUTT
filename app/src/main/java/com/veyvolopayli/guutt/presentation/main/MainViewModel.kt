package com.veyvolopayli.guutt.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.guutt.domain.usecases.GetCurrentGroupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentGroupUseCase: GetCurrentGroupUseCase
): ViewModel() {
    // todo Проверка интернет соединения
    // todo Проверка наличия группы в SP -> 2 сценария навигации: Экран авторизации (WebView); Домашний экран с расписанием

    private val _currentGroup = MutableLiveData<String?>()
    val currentGroup: LiveData<String?> = _currentGroup

    init {
        isUpdateAvailable()
        checkCurrentGroup()
        checkInternetConnection()
    }

    private fun checkCurrentGroup() {
        getCurrentGroupUseCase().onEach { g ->
            _currentGroup.value = g
        }.launchIn(viewModelScope)
    }

    private fun isUpdateAvailable() {

    }

    private fun checkInternetConnection(): Boolean {
        return true
    }
}