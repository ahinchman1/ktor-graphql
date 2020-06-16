package com.raywenderlich.favrwtutorials

import com.raywenderlich.favrwtutorials.data.models.AuthorId
import com.raywenderlich.favrwtutorials.data.models.Category
import com.raywenderlich.favrwtutorials.data.models.Tutorial
import com.raywenderlich.favrwtutorials.data.models.TutorialId
import com.raywenderlich.favrwtutorials.data.repository.Data.authors
import com.raywenderlich.favrwtutorials.data.repository.Data.tutorials
import com.raywenderlich.favrwtutorials.data.repository.TutorialRepository
import com.raywenderlich.favrwtutorials.data.repository.yearMonthDateFormat
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeDefinitionRegistry
import java.nio.file.Files
import java.nio.file.Path

private val parser = SchemaParser()

fun schemaDef(path: Path): TypeDefinitionRegistry = parser.parse(Files.newBufferedReader(path))

private val schemaDef = SchemaParser().parse("""

type Query {
    getTutorialAuthor(tutorialId: Int!): Author
}

type Mutation {

	addTutorial(
		id: Int!
		title: String!
        date: String!
        authorId: Int!
        category: Int!
        url: String!
	): Tutorial

	updateAuthorTutorials(
	    authorId: Int!
	    tutorialId: Int!
	): Tutorial
}

type Tutorial {
	id: Int!
	title: String!
	date: String!
	author: Author
}

type Author {
	id: Int!
	name: String!
	tutorials: [Int]
}

schema {
	query: Query
	mutation: Mutation
}
""")

var runtimeWiring = RuntimeWiring.newRuntimeWiring()
    .type("Query") { builder ->
        builder.dataFetcher("getTutorials") {
            TutorialRepository.getTutorials()
        }

        builder.dataFetcher("getTutorialAuthor") { env ->
            val tutorialId = env.getArgument<TutorialId>("tutorialId")
            TutorialRepository.getAuthorTutorials(tutorialId)
        }

        builder.dataFetcher("getAuthors") {
            TutorialRepository.getAuthors()
        }

        builder.dataFetcher("getAuthorTutorials") { env ->
            val authorId = env.getArgument<AuthorId>("authorId")
            TutorialRepository.getAuthorTutorials(authorId)
        }
    }
    .type("Mutation") { builder ->
        builder.dataFetcher("addTutorial") { env ->
            val name = env.getArgument<String>("name")
            val id = env.getArgument<TutorialId>("id")
            val title = env.getArgument<String>("title")
            val date = env.getArgument<String>("date")
            val authorId = env.getArgument<AuthorId>("authorId")
            val category = Category.fromStorage(env.getArgument<Int>("category"))
            val url = env.getArgument<String>("url")

            when {
                name.isNullOrEmpty() ->  throw Exception("You must specify a non-empty name.")
                id == null -> throw Exception("You must specify a non-empty id.")
                tutorials.firstOrNull { id == it.id } != null -> throw Exception("Tutorial id already exists. Please use a different one.")
                title.isNullOrEmpty() -> throw Exception("You must specify a non-empty title.")
                yearMonthDateFormat.parse(date) == null -> throw Exception("Unable to parse date $date for the format 'MM/d/yyyy'")
                authorId == null -> throw Exception("You must specify a non-empty name")
                authors.firstOrNull { authorId == it.id } == null -> throw Exception("Author id does not exist. Please check to make sure the proper author id is being used.")
                url.isNullOrEmpty() ->  throw Exception("You must specify a non-empty id.")
            }

            val tutorial = Tutorial(id, title, yearMonthDateFormat.parse(date)!!, authorId, category, url)

            TutorialRepository.addTutorial(tutorial)
        }
    }
    .build()


var graphQLSchema = SchemaGenerator().makeExecutableSchema(schemaDef, runtimeWiring)