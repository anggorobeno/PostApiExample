package com.example.learndaggerhilt.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    var message: String,
    @SerializedName("statusCode")
    val code: Int,
    @SerializedName("password")
    var password: String,

    @SerializedName("data")
    var data : Data
) {
    data class Data (val token : String)
}
