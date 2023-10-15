package com.veyvolopayli.guutt.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.UUID

@Parcelize
data class Day3(
    val id: String = UUID.randomUUID().toString(),
//    val date: String,
    val lessons: @RawValue List<Lesson>,
    val weekType: Int, // 1 если нечетная, 2 если четная
    val weekPosition: Int,
    val dayOfWeek: String = when(weekPosition) {
        1 -> "ПН"
        2 -> "ВТ"
        3 -> "СР"
        4 -> "ЧТ"
        5 -> "ПТ"
        6 -> "СБ"
        7 -> "ВС"
        else -> "ВС"
    }
): Parcelable {

    fun toParsed(): ParsedDay {
        return ParsedDay(
            id = this.id,
//            date = this.date,
            lessons = Json.encodeToString(this.lessons),
            weekType = this.weekType,
            weekPosition = this.weekPosition,
            dayOfWeek = this.dayOfWeek
        )
    }

}