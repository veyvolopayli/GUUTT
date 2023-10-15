package com.veyvolopayli.guutt.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.json.Json
import java.util.UUID

@Entity
data class ParsedDay(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
//    val date: String,
    val lessons: String,
    val weekType: Int, // 1 если нечетная, 2 если четная
    val weekPosition: Int,
    val dayOfWeek: String
) {
    fun toDay(): Day3 {
        return Day3(
            id = this.id,
//            date = this.date,
            lessons = Json.decodeFromString<List<Lesson>>(this.lessons),
            weekType = this.weekType,
            weekPosition = this.weekPosition,
            dayOfWeek = this.dayOfWeek
        )
    }
}