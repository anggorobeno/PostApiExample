package com.example.learndaggerhilt.data.api

import com.example.learndaggerhilt.data.model.*
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): Response<List<User>> {
        return apiService.getUsers()
    }

    override suspend fun postRegister(request: RegisterRequest): Response<StatusResponse> {
        return apiService.postRegister(request)
    }

    override suspend fun postLogin(request: LoginRequest): Response<LoginResponse> {
        return apiService.postLogin(request)
    }
}