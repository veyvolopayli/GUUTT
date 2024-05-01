package com.veyvolopayli.guutt.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity("class_notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val classTitle: String,
    val classDateTime: LocalDateTime,
    val content: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
