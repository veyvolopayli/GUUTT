package com.veyvolopayli.guutt.domain.model

import java.time.LocalDate

data class Day(
    val date: LocalDate,
    val classes: List<ClassObject>
): java.io.Serializable