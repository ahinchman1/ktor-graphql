package com.raywenderlich.favrwtutorials

import com.github.pgutkowski.kgraphql.KGraphQL
import com.raywenderlich.favrwtutorials.data.RWStorage
import com.raywenderlich.favrwtutorials.data.models.AuthorId
import com.raywenderlich.favrwtutorials.data.models.TutorialId
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import raywenderlich.data.Author
import raywenderlich.data.Tutorial

@KtorExperimentalLocationsAPI
@Location("/graphql")
data class GraphQLRequest(val query: String = "")

class GraphQLSchema(private val storage: RWStorage) {

    val schema = KGraphQL.schema {

        configure {
            useDefaultPrettyPrinter = true
        }

        // Define your types here

        type<Tutorial> {
            description = "A Ray Wenderlich Tutorial blog"
            property<Author>("author") {
                resolver { tutorial: Tutorial -> storage.getTutorialAuthor(tutorial.id.toInt())}
            }
        }

        type<Author> {
            description = "Ray Wenderlich Authors"
            property<List<Tutorial>>("tutorials") {
                resolver { author -> storage.getAuthorTutorials(author.author_id.toInt()) }
            }
        }

        // Tutorial Queries

        query("getTutorials") {
            description = "Gets all saved Ray Wenderlich tutorials."
            resolver(storage::getTutorials)
        }

        mutation("addTutorial") {
            description = "Gets the author of a particular tutorial's information."
            resolver { tutorial: Tutorial -> storage.addTutorial(tutorial) }
        }

        // Author Queries

        query("getAuthors") {
            description = "Gets all of the saved Ray Wenderlich authors."
            resolver(storage::getAuthors)
        }

        mutation("updateAuthorTutorials") {
            description = "Adds a new tutorial to the list of tutorials a particular author writes."
            resolver { authorId: AuthorId, tutorialId: TutorialId -> storage.updateAuthorTutorials(authorId, tutorialId) }
        }
    }

}