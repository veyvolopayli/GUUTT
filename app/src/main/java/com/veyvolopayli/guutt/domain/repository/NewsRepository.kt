package com.veyvolopayli.guutt.domain.repository

import com.veyvolopayli.guutt.domain.model.News

interface NewsRepository {
    suspend fun getAllNews(): List<News>
}