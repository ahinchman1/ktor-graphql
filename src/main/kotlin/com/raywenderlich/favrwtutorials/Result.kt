package com.raywenderlich.favrwtutorials

import mu.KotlinLogging

sealed class Result<out R> {
    data class Success<out T>(val data: T): Result<T>()
    data class Failure(val error: Throwable?): Result<Nothing>()
}

val logger by lazy { KotlinLogging.logger { } }