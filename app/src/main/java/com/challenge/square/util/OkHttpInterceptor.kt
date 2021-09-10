package com.challenge.square.util

import okhttp3.Interceptor
import okhttp3.Response

class OkHttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder().addHeader("Authentication", "Bearer ${Constants.HEADER_API_KEY}").build()
        return chain.proceed(request)
    }
}