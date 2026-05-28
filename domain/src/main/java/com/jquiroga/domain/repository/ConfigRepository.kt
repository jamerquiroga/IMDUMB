package com.jquiroga.domain.repository

import io.reactivex.Completable

interface ConfigRepository {

    fun fetchAndPersistRemoteConfig(): Completable

}