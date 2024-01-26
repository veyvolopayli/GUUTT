package com.veyvolopayli.guutt.domain.repository

interface PrefsRepository {
    fun getString(key: String, defaultValue: String? = null): String?
    fun setString(key: String, value: String)
    fun clearPrefs()
}