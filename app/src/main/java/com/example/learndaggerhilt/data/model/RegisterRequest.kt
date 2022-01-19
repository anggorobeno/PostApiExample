package com.example.learndaggerhilt.data.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("username")
    var userName: String,

    @SerializedName("password")
    var password: String,

    @SerializedName("email")
    var email: String
)
