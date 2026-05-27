package com.jquiroga.imdumb.di

import com.jquiroga.domain.repository.MovieRepository
import com.jquiroga.domain.usecase.GetCategoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetCategoriesUseCase(
        movieRepository: MovieRepository
    ): GetCategoriesUseCase {
        return GetCategoriesUseCase(movieRepository)
    }
}