package com.raywenderlich.favrwtutorials.data.repository

import com.raywenderlich.favrwtutorials.data.models.Author
import com.raywenderlich.favrwtutorials.data.models.Category
import com.raywenderlich.favrwtutorials.data.models.Tutorial
import java.text.SimpleDateFormat
import java.util.*

val yearMonthDateFormat = SimpleDateFormat("MM/d/yyyy", Locale.US)

object Data {
    var authors: List<Author> = createAuthors()
    var tutorials: List<Tutorial> = createTutorials()
}

private fun createAuthors() =
    listOf(
        Author(
            id = 0,
            name = "Kevin Moore",
            tutorials = listOf(1)
        ),
        Author(
            id = 1,
            name = "Victoria Gonda",
            tutorials = listOf(2, 3)
        ),
        Author(
            id = 2,
            name = "Ryan Ackermann",
            tutorials = listOf(4)
        )
    )

private fun createTutorials() =
    listOf(
        Tutorial(
            id = 1,
            title = "Elegant Networking in Flutter with Chopper",
            date = yearMonthDateFormat.parse("6/9/2020"),
            authorId = 0,
            category = Category.FLUTTER,
            url = "https://www.raywenderlich.com/10099546-elegant-networking-in-flutter-with-chopper"
        ),
        Tutorial(
            id = 2,
            title = "Live Templates in Android Studio: Getting Started",
            date = yearMonthDateFormat.parse("11/2/2019"),
            authorId = 1,
            category = Category.ANDROID_AND_KOTLIN,
            url = "https://www.raywenderlich.com/4979242-live-templates-in-android-studio-getting-started"
        ),
        Tutorial(
            id = 3,
            title = "Espresso Testing and Screen Robots: Getting Started",
            date = yearMonthDateFormat.parse("2/27/2019"),
            authorId = 1,
            category = Category.ANDROID_AND_KOTLIN,
            url = "https://www.raywenderlich.com/949489-espresso-testing-and-screen-robots-getting-started"
        ),
        Tutorial(
            id = 4,
            title = "Routing with MapKit and Core Location",
            date = yearMonthDateFormat.parse("6/5/2020"),
            authorId = 3,
            category = Category.IOS_AND_KOTLIN,
            url = "https://www.raywenderlich.com/10028489-routing-with-mapkit-and-core-location"
        )
    )