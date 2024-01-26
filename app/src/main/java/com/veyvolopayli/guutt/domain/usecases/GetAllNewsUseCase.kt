package com.veyvolopayli.guutt.domain.usecases

import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.domain.model.News
import com.veyvolopayli.guutt.domain.repository.NewsRepository
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetAllNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke() = flow<Resource<List<News>>> {
        try {
            val news = repository.getAllNews()
            emit(Resource.Success(news))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}