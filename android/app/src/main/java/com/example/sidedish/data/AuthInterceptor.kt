package com.example.sidedish.data

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val appSession: AppSession
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val jwt = appSession.jwt

        val requestBuilder = chain.request()
            .newBuilder()

        jwt?.let {
            requestBuilder.addHeader("Authorization", "${it.tokenType} ${it.accessToken}")
        }

        return chain.proceed(requestBuilder.build())
    }
}