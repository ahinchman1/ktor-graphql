package com.raywenderlich.favrwtutorials.data.models

import com.raywenderlich.favrwtutorials.data.Category
import com.squareup.moshi.JsonClass
import java.util.*

typealias TutorialId = Int

@JsonClass(generateAdapter = true)
data class TutorialModel(
    val id: TutorialId,
    val title: String,
    val date: Date,
    val author: String,
    val category: Category,
    val url: String
)