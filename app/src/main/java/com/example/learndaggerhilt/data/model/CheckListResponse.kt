package com.example.learndaggerhilt.data.model

import com.google.gson.annotations.SerializedName

data class CheckListResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("errorMessage")
	val errorMessage: Any? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)

data class DataItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("items")
	val items: Any? = null,

	@field:SerializedName("checklistCompletionStatus")
	val checklistCompletionStatus: Boolean? = null
)
