package com.raywenderlich.favrwtutorials.data.adapters

import com.raywenderlich.favrwtutorials.data.models.AuthorId
import com.raywenderlich.favrwtutorials.data.models.TutorialId
import com.squareup.sqldelight.ColumnAdapter

val authorTutorialsAdapter = object : ColumnAdapter<List<TutorialId>, String> {
    override fun decode(databaseValue: String): List<TutorialId> = databaseValue.split(",").map { it.toInt() }
    override fun encode(value: List<TutorialId>): String = value.joinToString(separator = ",")
}