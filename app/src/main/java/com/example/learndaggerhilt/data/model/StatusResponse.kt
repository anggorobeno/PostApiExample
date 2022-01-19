package com.example.learndaggerhilt.data.model

import com.google.gson.annotations.SerializedName

data class StatusResponse(
    @SerializedName("statusCode")
    val code: Int
)
