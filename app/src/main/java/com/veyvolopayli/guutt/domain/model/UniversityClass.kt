package com.veyvolopayli.guutt.domain.model

data class UniversityClass(
    val id: Long,
    val title: String,
    val color: String,
    val start: String,
    val end: String,
    val description: ClassDescription
)