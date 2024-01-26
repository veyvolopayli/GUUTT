package com.veyvolopayli.guutt.domain.usecases

import com.veyvolopayli.guutt.domain.repository.PrefsRepository
import javax.inject.Inject

class ClearDataUseCase @Inject constructor(
    private val repository: PrefsRepository
) {
    operator fun invoke() {
        repository.clearPrefs()
    }
}