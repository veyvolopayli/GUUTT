package com.veyvolopayli.guutt.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime


@Entity
data class ClassDBO(
    @PrimaryKey val id: Long,
    val title: String,
    val color: String,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val building: String,
    val classroom: String,
    val event: String,
    val professor: String,
    val department: String,
    val group: String,
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