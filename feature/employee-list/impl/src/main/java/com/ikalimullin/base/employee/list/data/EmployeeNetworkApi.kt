package com.ikalimullin.base.employee.list.data

import com.ikalimullin.entity.network.NetworkResult
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

internal interface EmployeeNetworkApi {

    @GET("users")
    fun getEmployeeAsync(): Deferred<NetworkResult<EmployeeDto>>
}
