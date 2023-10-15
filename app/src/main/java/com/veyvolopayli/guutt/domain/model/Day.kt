package com.veyvolopayli.guutt.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.time.LocalDate

@Parcelize
data class Day(
    val date: LocalDate,
    val classes: @RawValue List<UniversityClass>
) : Parcelable