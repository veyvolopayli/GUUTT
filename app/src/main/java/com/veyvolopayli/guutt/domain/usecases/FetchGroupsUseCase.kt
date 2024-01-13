package com.veyvolopayli.guutt.domain.usecases

import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchGroupsUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(): Flow<Resource<List<String>>> = flow {
        val groupsResponse = repository.getGroups()
        if (groupsResponse.isSuccessful) {
            val groups = groupsResponse.body() ?: emptyList()
            emit(Resource.Success(groups))
        } else {
            when(groupsResponse.code()) {
                409 -> emit(Resource.Error("Server side error"))
            }
        }
    }
}