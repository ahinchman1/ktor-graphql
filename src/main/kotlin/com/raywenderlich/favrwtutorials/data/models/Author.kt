package com.raywenderlich.favrwtutorials.data.models

typealias AuthorId = Int

data class Author(
    val authorId: AuthorId,
    val name: String = "",
    val articles: List<String> = listOf()
)