package com.raywenderlich.favrwtutorials.data.repository

import com.raywenderlich.favrwtutorials.data.models.Author
import com.raywenderlich.favrwtutorials.data.models.AuthorId
import com.raywenderlich.favrwtutorials.data.models.Tutorial
import com.raywenderlich.favrwtutorials.data.models.TutorialId

interface RWStorage {

    // Gets all saved Ray Wenderlich tutorials.
    fun getTutorials(): List<Tutorial>

    // Gets the author associated with a particular tutorial.
    fun getTutorialAuthor(id: TutorialId): Author?

    // Gets the author of a particular tutorial's information.
    fun addTutorial(tutorial: Tutorial)

    // Gets all of the saved Ray Wenderlich authors.
    fun getAuthors(): List<Author>

    // Gets all the tutorials written by a particular author
    fun getAuthorTutorials(id: AuthorId): List<Tutorial>

    // Adds a new tutorial to the list of tutorials a particular author writes
    fun updateAuthorTutorials(authorId: AuthorId, tutorialId: TutorialId)
}