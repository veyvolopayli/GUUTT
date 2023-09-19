package com.veyvolopayli.guutt.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.UUID

@Parcelize
data class Day(
    val id: String = UUID.randomUUID().toString(),
    val date: String,
    val dayOfWeek: String,
    val lessons: @RawValue List<Lesson>
): Parcelable