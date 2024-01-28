package com.veyvolopayli.guutt.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ClassObject(
    val id: Long,
    val title: String,
    val color: String,
    val start: String,
    val end: String,
    val description: ClassDescription
) {
    fun toClassObjectWithNote(group: String): ClassObjectWithNote {
        return ClassObjectWithNote(
            id = this.id,
            title = this.title,
            color = this.color,
            start = this.start,
            end = this.end,
            building = this.description.building,
            classroom = this.description.classroom,
            event = this.description.event,
            professor = this.description.professor,
            department = this.description.department,
            group = group,
            note = ""
        )
    }
}