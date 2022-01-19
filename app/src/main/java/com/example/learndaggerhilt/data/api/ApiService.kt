package com.example.learndaggerhilt.data.api

import com.example.learndaggerhilt.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @POST("register")
    suspend fun postRegister(@Body request : RegisterRequest): Response<StatusResponse>

    @POST("login")
    suspend fun postLogin(@Body request : LoginRequest): Response<LoginResponse>

    @GET("checklist")
    suspend fun getCheckList(@Header("Authorization") token: String) : Response<CheckListResponse>


}