package com.veyvolopayli.guutt.presentation.choose_group_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.domain.usecases.FetchGroupsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChooseGroupViewModel @Inject constructor(
    private val fetchGroupsUseCase: FetchGroupsUseCase
): ViewModel() {

    private val _groupsState: MutableLiveData<List<String>> = MutableLiveData()
    val groupsState: LiveData<List<String>> = _groupsState

    private val _toastMessage: MutableLiveData<String> = MutableLiveData()
    val toastMessage: LiveData<String> = _toastMessage

    private val _selectedGroup = MutableLiveData<String>()
    val selectedGroup: LiveData<String> = _selectedGroup

    init {
        fetchGroups()
    }

    fun setSelectedGroup(group: String) {
        _selectedGroup.value = group
    }

    private fun fetchGroups() {
        fetchGroupsUseCase().onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    _groupsState.value = resource.data ?: emptyList()
                }
                is Resource.Error -> {
                    _toastMessage.value = resource.error ?: ""
                }
            }
        }.launchIn(viewModelScope)
    }
}