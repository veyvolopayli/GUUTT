package com.veyvolopayli.guutt.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UniversityClassDto(
    val id: Long,
    val title: String,
    val color: String,
    val start: String,
    val end: String,
    val description: String
) {
    fun toUniversity(description: ClassDescription): UniversityClass {
        return UniversityClass(
            id = this.id,
            title = this.title,
            color = this.color,
            start = this.start,
            end = this.end,
            description = description
        )
    }
}