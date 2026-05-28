package com.jquiroga.data.di

import android.content.Context
import android.content.SharedPreferences
import com.jquiroga.data.datasource.local.AppPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(AppPreferencesManager.PREFS_NAME, Context.MODE_PRIVATE)
    }
}
