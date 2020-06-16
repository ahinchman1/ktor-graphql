package com.raywenderlich.favrwtutorials.playground

import com.raywenderlich.favrwtutorials.logger
import graphql.ExceptionWhileDataFetching
import graphql.GraphQLError


val playgroundErrorFormat: (GraphQLError.() -> Map<String, Any>) =  {
    val clientMessage = if (this is ExceptionWhileDataFetching) {

        val formattedMessage = if (exception is Error) {
            logger.debug { exception.message }
            exception.message
        } else {
            logger.debug { }
            "Internal server error"
        }

        formattedMessage
    } else {
        message
    }

    val result = toSpecification()
    result["message"] = clientMessage
    result
}