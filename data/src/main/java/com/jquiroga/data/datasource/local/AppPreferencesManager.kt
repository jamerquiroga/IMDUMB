package com.jquiroga.data.datasource.local

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class AppPreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun saveRemoteConfig(welcomeMessage: String) {
        sharedPreferences.edit {
            putString(KEY_WELCOME_MESSAGE, welcomeMessage)
        }
    }

    companion object {
        const val PREFS_NAME = "imdumb_prefs"
        const val KEY_WELCOME_MESSAGE = "welcome_message"
    }
}
