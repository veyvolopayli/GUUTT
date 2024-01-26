package com.veyvolopayli.guutt.domain.usecases

import com.veyvolopayli.guutt.common.Constants
import com.veyvolopayli.guutt.domain.repository.PrefsRepository
import javax.inject.Inject

class SetCurrentGroupUseCase @Inject constructor(
    private val repository: PrefsRepository
) {
    operator fun invoke(newGroup: String) {
        repository.setString(Constants.CURRENT_GROUP, newGroup)
    }
}