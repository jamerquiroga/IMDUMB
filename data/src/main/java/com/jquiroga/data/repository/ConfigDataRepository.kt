package com.jquiroga.data.repository

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.jquiroga.data.datasource.local.AppPreferencesManager
import com.jquiroga.domain.repository.ConfigRepository
import io.reactivex.Completable
import javax.inject.Inject

class ConfigDataRepository @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
    private val preferencesManager: AppPreferencesManager
) : ConfigRepository {

    override fun fetchAndPersistRemoteConfig(): Completable {
        return Completable.create { emitter ->
            firebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        persistConfigValues()
                        if (!emitter.isDisposed) emitter.onComplete()
                    } else {
                        Log.e(TAG, "Remote Config fetch failed", task.exception)
                        persistConfigValues()
                        if (!emitter.isDisposed) emitter.onComplete()
                    }
                }
                .addOnFailureListener { error ->
                    Log.e(TAG, "Remote Config fetch failure listener", error)
                    persistConfigValues()
                    if (!emitter.isDisposed) emitter.onComplete()
                }
        }
    }

    private fun persistConfigValues() {
        val welcomeMessage =
            firebaseRemoteConfig.getString(AppPreferencesManager.KEY_WELCOME_MESSAGE)

        preferencesManager.saveRemoteConfig(welcomeMessage = welcomeMessage)
    }

    companion object {
        private const val TAG = "AppConfigRepository"
    }
}