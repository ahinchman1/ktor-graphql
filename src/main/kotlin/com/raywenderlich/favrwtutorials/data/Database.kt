package com.raywenderlich.favrwtutorials.data

import com.raywenderlich.favrwtutorials.logger
import com.raywenderlich.favrwtutorials.sqldelight.raywenderlich.database.RayWenderlichDb
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import raywenderlich.data.Author
import raywenderlich.data.Tutorial
import java.io.IOException

class Database: RWStorage {
    private var rwDatabase: RayWenderlichDb? = null

    val instance: RayWenderlichDb?
        get() = rwDatabase

    suspend fun createDB() {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)

        try {
            val database = createQueryWrapper(driver)
            rwDatabase = database
        } catch (e: IOException) {
            logger.debug { "Unable to parse content into DB. Cause of error: $e" }
        }
    }

    override fun getTutorials(): List<Tutorial> {
        TODO("Not yet implemented")
    }

    override fun getTutorialAuthor(id: Int) {
        TODO("Not yet implemented")
    }

    override fun addTutorial(tutorial: Tutorial) {
        TODO("Not yet implemented")
    }

    override fun getAuthors(): List<Author> {
        TODO("Not yet implemented")
    }

    override fun updateAuthorArticles(authorId: Int, articleId: Int) {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}