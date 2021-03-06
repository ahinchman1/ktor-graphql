/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.favrwtutorials.data.repository

import com.raywenderlich.favrwtutorials.data.models.*
import com.raywenderlich.favrwtutorials.data.repository.Data.authors
import com.raywenderlich.favrwtutorials.data.repository.Data.tutorials
import java.util.*

/**
 * Resolvers tells the server how to populate data for a request
 */
object Resolvers: RWRepository {

    override fun getTutorials(): List<Tutorial> {
        return tutorials
    }

    override fun getTutorialAuthor(id: TutorialId): Author? {
        return authors.firstOrNull() { author -> author.tutorials.contains(id) }
    }

    override fun addTutorial(id: TutorialId, title: String, date: Date, authorId: AuthorId, category: Category, url: String?): Tutorial {
        val tut = Tutorial(id, title, date, id, category, url)
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
