package com.raywenderlich.favrwtutorials

import com.github.pgutkowski.kgraphql.KGraphQL
import com.raywenderlich.favrwtutorials.data.RWStorage
import raywenderlich.data.Author
import raywenderlich.data.Tutorial
import java.util.*

class GraphQLSchema(private val storage: RWStorage) {

    val schema = KGraphQL.schema {

        configure {
            useDefaultPrettyPrinter = true
        }

        // Define your types here

        type<Tutorial> {
            description = "A Ray Wenderlich Tutorial blog"

            property<Author>("author") {
                resolver { tutorial: Tutorial -> storage.getTutorialAuthor(tutorial.id)}
            }
        }

        type<Author> {
            description = "Ray Wenderlich Authors"

            property<Tutorial>("tutorials") {
                resolver { author -> storage.getAuthorTutorials(author.id) }
            }
        }

        mutation("login") {
            resolver { username: String, password: String ->
                // Do your login logic
                // returns User
            }
        }

        query("getArticles") {
            resolver { }.withArgs {
                arg { }
            }
        }

    }

}