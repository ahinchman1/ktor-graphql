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
package com.raywenderlich.favrwtutorials.playground

import com.raywenderlich.favrwtutorials.data.models.AuthorId
import com.raywenderlich.favrwtutorials.data.models.Category
import com.raywenderlich.favrwtutorials.data.models.TutorialId
import com.raywenderlich.favrwtutorials.data.repository.Data.authors
import com.raywenderlich.favrwtutorials.data.repository.Data.tutorials
import com.raywenderlich.favrwtutorials.data.repository.Resolvers
import com.raywenderlich.favrwtutorials.data.repository.yearMonthDateFormat
import graphql.schema.GraphQLSchema
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeDefinitionRegistry
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.collections.LinkedHashMap

// Reads the graphql file in resources to load the schema
val schemaDef: TypeDefinitionRegistry by lazy {
    val classloader = Thread.currentThread().contextClassLoader
    val bufferedReader = InputStreamReader(classloader.getResourceAsStream("rwtutorial.graphql")!!).apply { BufferedReader(this) }
    SchemaParser().parse(bufferedReader)
}

// Wires expected behavior for GraphQL
val runtimeWiring: RuntimeWiring = RuntimeWiring.newRuntimeWiring()
    .type("Query") { builder ->
        builder.dataFetcher("getTutorials") {
            Resolvers.getTutorials()
        }

        builder.dataFetcher("getTutorialAuthor") { env ->
            val tutorialId = env.getArgument<TutorialId>("tutorialId")
            Resolvers.getTutorialAuthor(tutorialId)
        }

        builder.dataFetcher("getAuthors") {
            Resolvers.getAuthors()
        }

        builder.dataFetcher("getAuthorTutorials") { env ->
            val authorId = env.getArgument<AuthorId>("authorId")
            Resolvers.getAuthorTutorials(authorId)
        }
    }
    .type("Mutation") { builder ->
        builder.dataFetcher("addTutorial") { env ->
            val input = (env.arguments.get("input") as LinkedHashMap<*, *>)
            val id = input["id"] as? TutorialId
            val title = input["title"] as? String
            val date = input["date"] as? String
            val authorId = input["authorId"] as? AuthorId
            val category = Category.fromStorage(input["category"] as? String ?: "")
            val url = input["url"] as? String

            when {
                id == null -> throw Exception("You must specify a non-empty id.")
                tutorials.firstOrNull { id == it.id } != null -> throw Exception("Tutorial id already exists. Please use a different one.")
                title.isNullOrEmpty() -> throw Exception("You must specify a non-empty title.")
                yearMonthDateFormat.parse(date) == null -> throw Exception("Unable to parse date $date for the format 'MM/d/yyyy'")
                authorId == null -> throw Exception("You must specify a non-empty name")
                authors.firstOrNull { authorId == it.id } == null -> throw Exception("Author id does not exist. Please check to make sure the proper author id is being used.")
                else -> Resolvers.addTutorial(id, title, yearMonthDateFormat.parse(date)!!, authorId, category, url)
            }
        }
    }.build()

val graphQLSchema: GraphQLSchema = SchemaGenerator().makeExecutableSchema(schemaDef, runtimeWiring)