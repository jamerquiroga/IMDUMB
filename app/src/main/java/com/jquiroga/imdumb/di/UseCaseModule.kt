package com.jquiroga.imdumb.di

import com.jquiroga.domain.repository.ConfigRepository
import com.jquiroga.domain.repository.MovieRepository
import com.jquiroga.domain.usecase.FetchRemoteConfigUseCase
import com.jquiroga.domain.usecase.GetCategoriesUseCase
import com.jquiroga.domain.usecase.GetMovieDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetCategoriesUseCase(
        movieRepository: MovieRepository
    ): GetCategoriesUseCase {
        return GetCategoriesUseCase(movieRepository)
    }

    @Provides
    fun provideGetMovieDetailUseCase(
        movieRepository: MovieRepository
    ): GetMovieDetailUseCase {
        return GetMovieDetailUseCase(movieRepository)
    }

    @Provides
    fun provideFetchRemoteConfigUseCase(
        configRepository: ConfigRepository
    ): FetchRemoteConfigUseCase {
        return FetchRemoteConfigUseCase(configRepository)
    }
}