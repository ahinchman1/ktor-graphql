package com.raywenderlich.favrwtutorials.data.models

import com.squareup.moshi.JsonClass

typealias AuthorId = Int

@JsonClass(generateAdapter = true)
data class Author(
    val id: AuthorId,
    val name: String = "",
    val tutorials: List<TutorialId> = listOf()
)