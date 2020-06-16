package com.raywenderlich.favrwtutorials.data

import raywenderlich.data.Author
import raywenderlich.data.Tutorial
import java.io.Closeable

interface RWStorage: Closeable {

    fun getTutorials(): List<Tutorial>

    fun getTutorialAuthor(id: Int): Author

    fun getAuthors(): List<Author>

    fun addTutorial(tutorial: Tutorial)

    fun updateAuthorTutorials(authorId: Int, tutorialId: Int)
}