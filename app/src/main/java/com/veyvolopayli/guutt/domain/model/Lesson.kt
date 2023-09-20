package com.veyvolopayli.guutt.domain.model

import java.util.UUID

data class Lesson(
    val id: String = UUID.randomUUID().toString(),
    val lessonTime: String,
    val lessonName: String,
    val professorFullName: String,
    val classroom: String,
    val week: Int,
    val type: String
)