package com.jquiroga.data.di

import com.jquiroga.data.repository.MovieDataRepository
import com.jquiroga.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindsModule {

    @Binds
    abstract fun bindMovieRepository(
        impl: MovieDataRepository
    ): MovieRepository

}