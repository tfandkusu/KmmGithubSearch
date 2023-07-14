package com.tfandkusu.kgs.feature.home

import kotlinx.coroutines.delay
import kotlin.coroutines.cancellation.CancellationException

object ExampleException : Exception()

class ExampleUseCase {

    private val repository = ExampleRepository()

    @Throws(
        CancellationException::class,
        ExampleException::class,
    )
    suspend fun execute(): Int {
        return repository.get()
    }
}

class ExampleRepository {

    suspend fun get(): Int {
        delay(1000)
        throw ExampleException
        return 1
    }
}
