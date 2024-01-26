package com.veyvolopayli.guutt.data.repository

import android.content.SharedPreferences
import com.veyvolopayli.guutt.domain.repository.PrefsRepository
import javax.inject.Inject

class PrefsRepositoryImpl @Inject constructor(
    private val prefs: SharedPreferences
) : PrefsRepository {
    override fun getString(key: String, defaultValue: String?): String? {
        return prefs.getString(key, defaultValue)
    }

    override fun setString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    override fun clearPrefs() {
        prefs.edit().clear().apply()
    }
}