package com.veyvolopayli.guutt.presentation.search_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.domain.model.ClassObject
import com.veyvolopayli.guutt.domain.usecases.GetClassesUseCase
import com.veyvolopayli.guutt.domain.usecases.GetCurrentGroupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getClassesUseCase: GetClassesUseCase,
    private val getCurrentGroupUseCase: GetCurrentGroupUseCase
) : ViewModel() {

    private val _classes = MutableLiveData<List<ClassObject>>()
    val classes: LiveData<List<ClassObject>> = _classes

    private val _searchedClasses = MutableLiveData<List<ClassObject>>()
    val searchedClasses: LiveData<List<ClassObject>> = _searchedClasses

    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String> = _toast

    init {
        getClasses()
    }

    private fun getClasses() {
        getCurrentGroupUseCase().onEach { group ->
            getClassesUseCase(group ?: "").onEach { resource ->
                when(resource) {
                    is Resource.Success -> {
                        val classesMap = resource.data ?: emptyMap()
                        val classesList = classesMap.values.flatten()
                        _classes.value = classesList
                        println(classesList)
                    }
                    is Resource.Error -> {
                        _toast.value = "Unexpected error"
                    }
                }
            }.launchIn(viewModelScope)
        }.launchIn(viewModelScope)
    }

    fun searchClasses(query: String) {
        val tQuery = query.trim().lowercase()
        if (tQuery.isEmpty()) {
            _searchedClasses.value = emptyList()
            return
        }
        val allClasses = _classes.value ?: emptyList()
        println(allClasses)
        val classesByQuery = allClasses.filter {
            it.title.lowercase().contains(tQuery)
                    || it.description.event.lowercase().contains(tQuery)
                    || it.description.professor.lowercase().contains(tQuery)
                    || it.description.classroom.lowercase().contains(tQuery)
                    || it.description.building.lowercase().contains(tQuery)
                    || it.description.department.lowercase().contains(tQuery)
        }
        println(classesByQuery)
        _searchedClasses.value = classesByQuery
    }

}