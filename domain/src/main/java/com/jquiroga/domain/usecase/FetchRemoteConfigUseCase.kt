package com.jquiroga.domain.usecase

import com.jquiroga.domain.repository.ConfigRepository
import io.reactivex.Completable

class FetchRemoteConfigUseCase (
    private val appConfigRepository: ConfigRepository
) {
    operator fun invoke(): Completable = appConfigRepository.fetchAndPersistRemoteConfig()
}
