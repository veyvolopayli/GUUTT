package com.veyvolopayli.guutt.domain.usecases

import com.veyvolopayli.guutt.domain.model.ClassObject
import com.veyvolopayli.guutt.domain.repository.DbClassesRepository
import javax.inject.Inject

class SaveClassesToDbUseCase @Inject constructor(
    private val repository: DbClassesRepository
) {
    suspend operator fun invoke(group: String, classes: List<ClassObject>) {
        repository.insertClasses(group, classes)
    }
}