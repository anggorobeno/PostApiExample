package com.example.learndaggerhilt.data

import com.example.learndaggerhilt.data.api.ApiHelper
import com.example.learndaggerhilt.data.api.ApiHelperImpl
import com.example.learndaggerhilt.data.model.LoginRequest
import com.example.learndaggerhilt.data.model.RegisterRequest
import com.example.learndaggerhilt.data.model.User
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelperImpl: ApiHelper) {
    suspend fun getUsers(): Response<List<User>> = apiHelperImpl.getUsers()
    suspend fun postLogin(request: LoginRequest) = apiHelperImpl.postLogin(request)
    suspend fun postRegister(request: RegisterRequest) = apiHelperImpl.postRegister(request)
}