package com.ikalimullin.base.employee.list.data

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

internal interface EmployeeNetworkApi {

    @GET
    suspend fun getEmployeeAsync(): Deferred<Result<List<EmployeeDto>>>
}
