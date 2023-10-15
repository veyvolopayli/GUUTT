package com.veyvolopayli.guutt.domain.usecases

import android.util.Log
import com.veyvolopayli.guutt.domain.model.ClassDescription
import com.veyvolopayli.guutt.domain.model.UniversityClass
import com.veyvolopayli.guutt.domain.model.UniversityClassDto
import com.veyvolopayli.guutt.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.lang.Exception
import java.time.LocalDate
import java.util.regex.Pattern
import javax.inject.Inject

class GetClassesUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(): Flow<List<List<UniversityClass>>?> = flow {
        try {
            val response =
                repository.getClasses("PHPSESSID=2341mjbkgtbatcaiee6jh5lqd2tkcrnr; _csrf=26f86f93a3cd25b363694715f0469d036e75e20871b0b3aee3b8ebcd10e5bacaa%3A2%3A%7Bi%3A0%3Bs%3A5%3A%22_csrf%22%3Bi%3A1%3Bs%3A32%3A%22KD74HWCkd3cEQtD58NYaHc3JVB9VPsTH%22%3B%7D; modal_mess=2e2e2d42a8ccfdfc06500ab24ea5a50ab0be5681449b0e01e7ac8734f1492050a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22modal_mess%22%3Bi%3A1%3Bs%3A10%3A%222023-10-13%22%3B%7D; session-cookie=178db9e9dff9f54f3863fc6d18991a243f779959478b04fa805283073c86fb8d31dafa52f79cbe739d7ec8a082784e5d")
            val htmlData = response.body()?.string() ?: run {
                Log.e("ERROR", "${response.code()} ${response.message()} ${response.errorBody()}")
                throw Exception("Failed")
            }
            val cookie = response.headers()
            Log.e("Response cookies", cookie.toString())
            val doc = Jsoup.parse(htmlData)

            // Find the script tag containing the "events" data
            val scriptElement: Element? = doc.select("script:containsData(events)").first()

            if (scriptElement != null) {
                // Extract the content of the script tag
                val scriptContent = scriptElement.data()

                // Assuming the "events" data is in a JavaScript object, you can use a regular expression to extract it
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
                    emit(groupClasses(classes))
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

}