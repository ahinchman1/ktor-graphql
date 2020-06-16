package com.raywenderlich.favrwtutorials

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Request(
    val userId: String,
    val packageName: String,
    val productId: String,
    val token: String
)