package com.veyvolopayli.guutt.common.serializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LocalDateGsonSerializer : JsonDeserializer<LocalDate> {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDate = LocalDate.parse(json?.asString, formatter)
}