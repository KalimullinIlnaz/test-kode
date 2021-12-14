package com.ikalimullin.entity.network

sealed class NetworkResult<out T> {
    data class Success<T>(val value: T) : NetworkResult<T>()
    data class Error(val error: Throwable) : NetworkResult<Nothing>()
}
