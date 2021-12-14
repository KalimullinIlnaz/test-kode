package com.ikalimullin.core.network

import com.ikalimullin.entity.network.NetworkResult
import kotlinx.coroutines.Deferred

suspend fun <R, D> Deferred<NetworkResult<R>>.awaitResponse(transform: suspend R.() -> D) =
    when (val response = await()) {
        is NetworkResult.Success -> NetworkResult.Success(response.value.transform())
        is NetworkResult.Error -> NetworkResult.Error(response.error)
    }
