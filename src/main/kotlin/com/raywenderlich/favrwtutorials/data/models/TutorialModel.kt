package com.raywenderlich.favrwtutorials.data.models

import com.raywenderlich.favrwtutorials.data.Category
import java.util.*

typealias TutorialId = Int

data class TutorialModel(
    val id: TutorialId,
    val title: String,
    val date: Date,
    val author: String,
    val category: Category,
    val url: String
)