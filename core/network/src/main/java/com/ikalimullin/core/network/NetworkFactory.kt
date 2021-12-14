package com.ikalimullin.core.network

import com.ikalimullin.entity.network.NetworkResult
import retrofit2.Response

object NetworkFactory {

    fun <T> create(response: Response<T>): NetworkResult<T> {
        val body = response.body()
        return if (body != null) {
            NetworkResult.Success<T>(body)
        } else {
            NetworkResult.Error(Throwable())
        }
    }

    fun <T> create(throwable: Throwable): NetworkResult<T> {
        return NetworkResult.Error(throwable)
    }
}
