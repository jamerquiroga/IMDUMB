package com.jquiroga.data.core.interceptor

import com.jquiroga.data.core.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${Constants.AUTH_TOKEN}")
            .addHeader("accept", "application/json")
            .build()
        return chain.proceed(request)
    }
}
