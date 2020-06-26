package com.raywenderlich.favrwtutorials.data.models

import com.squareup.moshi.JsonClass
import java.util.*

typealias TutorialId = Int

@JsonClass(generateAdapter = true)
data class Tutorial(
    val id: TutorialId,
    val title: String,
    val date: Date,
    val authorId: AuthorId,
    val category: Category,
    val url: String?
)