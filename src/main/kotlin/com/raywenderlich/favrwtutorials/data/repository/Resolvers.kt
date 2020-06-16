package com.raywenderlich.favrwtutorials.data.repository

import com.raywenderlich.favrwtutorials.data.models.*
import com.raywenderlich.favrwtutorials.data.repository.Data.authors
import com.raywenderlich.favrwtutorials.data.repository.Data.tutorials
import java.util.*

object Resolvers: RWRepository {

    override fun getTutorials(): List<Tutorial> {
        return tutorials
    }

    override fun getTutorialAuthor(id: TutorialId): Author? {
        return authors.firstOrNull() { author -> author.tutorials.contains(id) }
    }

    override fun addTutorial(
        id: TutorialId,
        title: String,
        date: Date,
        authorId: AuthorId,
        category: Category,
        url: String?
    ): Tutorial {
        val tut = Tutorial(
            id = id,
            title = title,
            date = date,
            authorId = id,
            category = category,
            url = url
        )
        tutorials = tutorials.plus(tut)
        return tut
    }

    override fun getAuthors(): List<Author> {
        return authors
    }

    override fun getAuthorTutorials(id: AuthorId): List<Tutorial> {
        val tutorialIdList: List<TutorialId> =  authors.firstOrNull { author -> author.id == id }?.tutorials ?: listOf()
        return tutorials.filter { tutorial -> tutorialIdList.any { it == tutorial.id} }
    }

    override fun updateAuthorTutorials(authorId: AuthorId, tutorialId: TutorialId) {
        val author = authors.firstOrNull { author -> author.id == authorId }
        author?.let {
            it.tutorials = it.tutorials + tutorialId
        }
    }
}
