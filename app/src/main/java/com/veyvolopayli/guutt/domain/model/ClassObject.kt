package com.veyvolopayli.guutt.domain.model

import com.veyvolopayli.guutt.common.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ClassObject(
    val id: Long,
    val title: String,
    val color: String,
    @Serializable(with = LocalDateTimeSerializer::class) val start: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class) val end: LocalDateTime,
    val description: ClassDescription
): java.io.Serializable