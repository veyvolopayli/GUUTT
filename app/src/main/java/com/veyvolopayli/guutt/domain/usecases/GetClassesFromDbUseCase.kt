package com.veyvolopayli.guutt.domain.usecases

import com.veyvolopayli.guutt.data.data_source.ClassesDAO
import com.veyvolopayli.guutt.domain.model.ClassObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetClassesFromDbUseCase @Inject constructor(
    private val classesDAO: ClassesDAO
) {
    operator fun invoke(group: String): Flow<List<ClassObject>> {
        val classDBOFlow = classesDAO.getGroupClasses(group)
        return classDBOFlow.map { classDbosList -> classDbosList.map { it.toClassObject() } }
    }
}