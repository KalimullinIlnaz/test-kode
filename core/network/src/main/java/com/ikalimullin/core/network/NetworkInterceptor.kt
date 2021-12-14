package com.ikalimullin.core.network

import okhttp3.Interceptor
import okhttp3.Response

private const val CONTENT_TYPE = "Content-Type"
private const val CONTENT_TYPE_VALUE = "application/json"

class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE)

        return chain.proceed(requestBuilder.build())
    }
}
