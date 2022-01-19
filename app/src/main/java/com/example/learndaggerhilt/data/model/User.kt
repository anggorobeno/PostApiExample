package com.example.learndaggerhilt.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("id")
    val id: Int = 0,
    @field:SerializedName("name")
    val name: String = "",
    @field:SerializedName("email")
    val email: String = "",
    @field:SerializedName("avatar")
    val avatar: String = ""
)
