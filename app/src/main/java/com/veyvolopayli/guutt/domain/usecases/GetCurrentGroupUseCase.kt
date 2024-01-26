package com.veyvolopayli.guutt.domain.usecases

import com.veyvolopayli.guutt.common.Constants
import com.veyvolopayli.guutt.domain.repository.PrefsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentGroupUseCase @Inject constructor(
    private val prefsRepository: PrefsRepository
) {
    operator fun invoke() = flow {
        val group = prefsRepository.getString(Constants.CURRENT_GROUP)
        emit(group)
    }
}