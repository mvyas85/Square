package com.challenge.square.service.api

import com.challenge.square.model.Employee
import com.challenge.square.model.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeApi {

    @GET("employees.json")
    suspend fun fetchEmployeeData(): Response<EmployeeResponse>

    @GET("employees_malformed.json")
    suspend fun fetchMalformedEmployeeData(): Response<EmployeeResponse>

    @GET("employees_empty.json")
    suspend fun fetchEmptyEmployeeData(): Response<EmployeeResponse>
}