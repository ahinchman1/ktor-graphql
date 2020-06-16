package com.raywenderlich.favrwtutorials.data.adapters

import com.squareup.sqldelight.ColumnAdapter
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

val yearMonthDateFormat = SimpleDateFormat("MM/d/yyyy", Locale.US)

fun String.toDate(): Date? = yearMonthDateFormat.parse(this)

val publishDateAdapter = object : ColumnAdapter<Date, String> {
    override fun decode(databaseValue: String): Date = databaseValue.toDate() ?: Date.from(Instant.now())

    override fun encode(value: Date): String = yearMonthDateFormat.format(value)
}

