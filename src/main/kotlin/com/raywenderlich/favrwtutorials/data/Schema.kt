package com.raywenderlich.favrwtutorials.data

import com.raywenderlich.favrwtutorials.data.adapters.authorTutorialsAdapter
import com.raywenderlich.favrwtutorials.data.adapters.publishDateAdapter
import com.raywenderlich.favrwtutorials.data.adapters.yearMonthDateFormat
import com.raywenderlich.favrwtutorials.sqldelight.raywenderlich.database.RayWenderlichDb
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import raywenderlich.data.Author
import raywenderlich.data.Tutorial

fun createQueryWrapper(driver: SqlDriver): RayWenderlichDb {
    return RayWenderlichDb(
        driver = driver,
        authorAdapter = Author.Adapter(
            authorTutorialsAdapter
        ),
        tutorialAdapter = Tutorial.Adapter(
            categoryAdapter = EnumColumnAdapter(),
            dateAdapter = publishDateAdapter
        )
    )
}
object Schema : SqlDriver.Schema by RayWenderlichDb.Schema {
    override fun create(driver: SqlDriver) {
        RayWenderlichDb.Schema.create(driver)

        // Seed data time!
        createQueryWrapper(driver).apply {

            tutorialQueries.insertTutorial(
                id = 1,
                title = "Elegant Networking in Flutter with Chopper",
                date = yearMonthDateFormat.parse("6/9/2020"),
                tutorial_author = 0,
                category = Category.FLUTTER,
                url = "https://www.raywenderlich.com/10099546-elegant-networking-in-flutter-with-chopper"
            )

            authorQueries.insertAuthor(
                author_id = 0,
                name = "Kevin Moore",
                articles = listOf(1)
            )

            tutorialQueries.insertTutorial(
                id = 2,
                title = "Live Templates in Android Studio: Getting Started",
                date = yearMonthDateFormat.parse("11/2/2019"),
                tutorial_author = 1,
                category = Category.ANDROID_AND_KOTLIN,
                url = "https://www.raywenderlich.com/4979242-live-templates-in-android-studio-getting-started"
            )

            authorQueries.insertAuthor(
                author_id = 1,
                name = "Victoria Gonda",
                articles = listOf(2, 3)
            )

            tutorialQueries.insertTutorial(
                id = 3,
                title = "Espresso Testing and Screen Robots: Getting Started",
                date = yearMonthDateFormat.parse("2/27/2019"),
                tutorial_author = 1,
                category = Category.ANDROID_AND_KOTLIN,
                url = "https://www.raywenderlich.com/949489-espresso-testing-and-screen-robots-getting-started"
            )

            tutorialQueries.insertTutorial(
                id = 4,
                title = "Routing with MapKit and Core Location",
                date = yearMonthDateFormat.parse("6/5/2020"),
                tutorial_author = 3,
                category = Category.IOS_AND_KOTLIN,
                url = "https://www.raywenderlich.com/10028489-routing-with-mapkit-and-core-location"
            )

            authorQueries.insertAuthor(
                author_id = 3,
                name = "Ryan Ackermann",
                articles = listOf(4)
            )
        }
    }
}