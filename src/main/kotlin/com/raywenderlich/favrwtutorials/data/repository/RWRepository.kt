package com.raywenderlich.favrwtutorials.data.repository

import com.raywenderlich.favrwtutorials.data.models.*
import java.util.*

interface RWRepository {

    // Gets all saved Ray Wenderlich tutorials.
    fun getTutorials(): List<Tutorial>

    // Gets the author associated with a particular tutorial.
    fun getTutorialAuthor(id: TutorialId): Author?

    // Adds a tutorial
    fun addTutorial(
        id: TutorialId,
        title: String,
        date: Date,
        authorId: AuthorId,
        category: Category,
        url: String?
    ): Tutorial

    // Gets all of the saved Ray Wenderlich authors.
    fun getAuthors(): List<Author>

    // Gets all the tutorials written by a particular author
    fun getAuthorTutorials(id: AuthorId): List<Tutorial>

    // Adds a new tutorial to the list of tutorials a particular author writes
    fun updateAuthorTutorials(authorId: AuthorId, tutorialId: TutorialId)
}