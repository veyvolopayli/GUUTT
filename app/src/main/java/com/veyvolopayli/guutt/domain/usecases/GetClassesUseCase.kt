package com.veyvolopayli.guutt.domain.usecases

import android.util.Log
import com.veyvolopayli.guutt.common.Constants
import com.veyvolopayli.guutt.domain.model.ClassDescription
import com.veyvolopayli.guutt.domain.model.Day
import com.veyvolopayli.guutt.domain.model.UniversityClass
import com.veyvolopayli.guutt.domain.model.UniversityClassDto
import com.veyvolopayli.guutt.domain.repository.MainRepository
import com.veyvolopayli.guutt.domain.repository.PrefsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.lang.Exception
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.regex.Pattern
import javax.inject.Inject

class GetClassesUseCase @Inject constructor(
    private val repository: MainRepository,
    private val prefs: PrefsRepository
) {
    operator fun invoke(): Flow<List<Day>?> = flow {
        try {
            val cookie = prefs.getString(Constants.COOKIE) ?: run {
                emit(null)
                return@flow
            }
            val response = repository.getClasses(cookie)
            val htmlData = response.body()?.string() ?: run {
                Log.e("ERROR", "${response.code()} ${response.message()} ${response.errorBody()}")
                throw Exception("Failed")
            }
//            val cookie = response.headers()
//            Log.e("Response cookies", cookie.toString())
            val doc = Jsoup.parse(htmlData)

            val scriptElement: Element? = doc.select("script:containsData(events)").first()

            if (scriptElement != null) {
                // Extract the content of the script tag
                val scriptContent = scriptElement.data()

                val pattern = "events : (\\[.*\\])"
                val matcher = Regex(pattern).find(scriptContent)

                val descriptionPattern = "<b>([^:]+):</b>\\s([^<]+)"

                val json = Json { ignoreUnknownKeys = true }

                if (matcher != null) {
                    val eventsData = matcher.groupValues[1]
                    val classes = json.decodeFromString<List<UniversityClassDto>>(eventsData)
                        .map { universityClassDto ->
                            val jsonObj = JSONObject()
                            val descriptionMatcher =
                                Pattern.compile(descriptionPattern).matcher(universityClassDto.description)

                            while (descriptionMatcher.find()) {
                                val key =
                                    descriptionMatcher.group(1) ?: throw Exception("Key is null")
                                val value = descriptionMatcher.group(2)
                                jsonObj.put(key, value)
                            }

                            with(jsonObj) {
                                universityClassDto.toUniversity(
                                    ClassDescription(
                                        building = if (has("Здание")) getString("Здание") else "",
                                        classroom = if (has("Аудитория")) getString("Аудитория") else "",
                                        event = if (has("Событие")) getString("Событие") else "",
                                        professor = if (has("Преподаватель")) getString("Преподаватель") else "",
                                        department = if (has("Кафедра")) getString("Кафедра") else ""
                                    )
                                )
                            }
                        }.filter { !it.start.contains("2021") && !it.start.contains("2022") }.sortedBy { it.start }
                    emit(fillGaps(groupClasses(classes)))
                    /*println(groupClasses(classes))
                    println(getListOfDates())*/
                } else {
                    println("Events data not found")
                    emit(null)
                }
            } else {
                println("Script tag containing events not found")
                emit(null)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            emit(null)
        }
    }

    private fun groupClasses(classes: List<UniversityClass>): List<List<UniversityClass>> {
        val groupedClasses = classes.groupBy { it.start.take(10) }
        return groupedClasses.values.toList()
    }

    private fun getListOfDates(): List<LocalDate> {
        val startDate = LocalDate.of(2023, 9, 1)
        val endDate = LocalDate.of(2023, 12, 31)

        val days = arrayListOf<LocalDate>()

        var currentDate = startDate

        while (!currentDate.isAfter(endDate)) {
            days.add(currentDate)
            currentDate = currentDate.plusDays(1)
        }

        return days
    }

    private fun fillGaps(classesWithGaps: List<List<UniversityClass>>): List<Day> {
        val fullyList = arrayListOf<Day>()
        getListOfDates().forEach { date ->
            val dayClasses = classesWithGaps.firstOrNull {
                val startDateTime = it.first().start
                val classDate = LocalDateTime.parse(startDateTime).toLocalDate()
                date.isEqual(classDate)
            }
            if (dayClasses != null) {
                fullyList.add(Day(date = date, classes = dayClasses))
            } else {
                fullyList.add(Day(date = date, classes = emptyList()))
            }
        }
        return fullyList
    }

}