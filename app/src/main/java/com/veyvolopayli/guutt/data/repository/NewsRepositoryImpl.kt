package com.veyvolopayli.guutt.data.repository

import com.veyvolopayli.guutt.data.remote.GuuTtApi
import com.veyvolopayli.guutt.domain.model.News
import com.veyvolopayli.guutt.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: GuuTtApi
) : NewsRepository {
    override suspend fun getAllNews(): List<News> {
        return api.getAllNews()
    }
}