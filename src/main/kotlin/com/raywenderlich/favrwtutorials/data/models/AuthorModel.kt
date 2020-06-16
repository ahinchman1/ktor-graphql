package com.raywenderlich.favrwtutorials.data.models

import com.squareup.moshi.JsonClass

typealias AuthorId = Int

@JsonClass(generateAdapter = true)
data class AuthorModel(
    val authorId: AuthorId,
    val name: String = "",
    val articles: List<String> = listOf()
)