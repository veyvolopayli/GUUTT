package com.veyvolopayli.guutt.common

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Converters {
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun toLocalDate(value: String): LocalDate {
        return LocalDate.parse(value, dateFormatter)
    }

    @TypeConverter
    fun fromLocalDate(date: LocalDate): String {
        return date.format(dateFormatter)
    }

    @TypeConverter
    fun toLocalDateTime(value: String): LocalDateTime {
        return LocalDateTime.parse(value, dateTimeFormatter)
    }

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime): String {
        return dateTime.format(dateTimeFormatter)
    }
}