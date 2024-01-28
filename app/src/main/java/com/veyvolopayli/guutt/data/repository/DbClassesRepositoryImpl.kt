package com.veyvolopayli.guutt.data.repository

import com.veyvolopayli.guutt.data.data_source.ClassesDAO
import com.veyvolopayli.guutt.domain.model.ClassObject
import com.veyvolopayli.guutt.domain.model.ClassObjectWithNote
import com.veyvolopayli.guutt.domain.repository.DbClassesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DbClassesRepositoryImpl @Inject constructor(
    private val dao: ClassesDAO
) : DbClassesRepository {
    override suspend fun getGroupClasses(group: String): Flow<List<ClassObjectWithNote>> {
        return dao.getGroupClasses(group)
    }

    override suspend fun insertClasses(group: String, classes: List<ClassObject>) {
        val classesWithNotes = classes.map { it.toClassObjectWithNote(group) }
        dao.insertClasses(classesWithNotes)
    }
}