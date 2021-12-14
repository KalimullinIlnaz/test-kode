package com.ikalimullin.core.network

import com.ikalimullin.entity.network.NetworkResult
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class NetworkCallAdapter<R>(
    private val responseType: Type,
) : CallAdapter<R, Deferred<NetworkResult<R>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): Deferred<NetworkResult<R>> {
        val deferred = CompletableDeferred<NetworkResult<R>>()

        deferred.invokeOnCompletion {
            if (deferred.isCancelled) {
                call.cancel()
            }
        }

        call.enqueue(object : Callback<R> {
            override fun onFailure(call: Call<R>, throwable: Throwable) {
                deferred.complete(NetworkFactory.create(throwable))
            }

            override fun onResponse(call: Call<R>, response: Response<R>) {
                deferred.complete(NetworkFactory.create(response))
            }
        })

        return deferred
    }
}
