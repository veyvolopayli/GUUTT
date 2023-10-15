package com.veyvolopayli.guutt.domain.model

sealed class SettingsItem {
    data class Lesson(
        val lessonTime: String = "",
        val lessonName: String = "",
        val professorFullName: String = "",
        val classroom: String = "",
        val week: Int = 0,
        val lessonType: String = "",
        val weekPosition: Int
    ) : SettingsItem()
    data class Header(val title: String) : SettingsItem()
}
