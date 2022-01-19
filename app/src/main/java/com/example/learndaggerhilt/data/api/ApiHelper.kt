package com.example.learndaggerhilt.data.api

import com.example.learndaggerhilt.data.model.*
import retrofit2.Response
import retrofit2.http.Body

interface ApiHelper {
    suspend fun getUsers(): Response<List<User>>
    suspend fun postRegister(request: RegisterRequest): Response<StatusResponse>
    suspend fun postLogin(request: LoginRequest): Response<LoginResponse>

}