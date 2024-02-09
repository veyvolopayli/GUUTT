package com.veyvolopayli.guutt.domain.usecases

import com.veyvolopayli.guutt.domain.model.ClassDescription
import com.veyvolopayli.guutt.domain.model.ClassObject
import com.veyvolopayli.guutt.domain.repository.DbClassesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetClassesFromDb @Inject constructor(
    private val repository: DbClassesRepository
) {
    suspend operator fun invoke(group: String): Flow<List<ClassObject>> {
        val classes = repository.getGroupClasses(group)
        return classes.map { list ->
            list.map { cWithNote ->
                ClassObject(
                    id = cWithNote.id,
                    title = cWithNote.title,
                    color = cWithNote.color,
                    start = cWithNote.start,
                    end = cWithNote.end,
                    description = ClassDescription(
                        building = cWithNote.building,
                        classroom = cWithNote.classroom,
                        event = cWithNote.event,
                        professor = cWithNote.professor,
                        department = cWithNote.department
                    )
                )
            }
        }
    }
}