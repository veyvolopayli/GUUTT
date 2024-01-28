package com.veyvolopayli.guutt.domain.model

import androidx.room.Entity


@Entity
data class ClassObjectWithNote(
    val id: Long,
    val title: String,
    val color: String,
    val start: String,
    val end: String,
    val building: String,
    val classroom: String,
    val event: String,
    val professor: String,
    val department: String,
    val group: String,
    val note: String
) {
    fun toClassObject(): ClassObject {
        return ClassObject(
            id = this.id,
            title = this.title,
            color = this.color,
            start = this.start,
            end = this.end,
            description = ClassDescription(
                building = this.building,
                classroom = this.classroom,
                event = this.event,
                professor = this.professor,
                department = this.department
            )
        )
    }
}