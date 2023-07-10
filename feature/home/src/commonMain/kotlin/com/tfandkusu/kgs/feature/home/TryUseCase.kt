package com.tfandkusu.kgs.feature.home

import kotlinx.coroutines.delay
import kotlin.coroutines.cancellation.CancellationException

object UnknownException : Exception()

class TryUseCase {

    private val repository = TryRepository()

    @Throws(
        CancellationException::class,
        UnknownException::class,
    )
    suspend fun execute(): Int {
        return repository.get()
    }
}

class TryRepository {

    suspend fun get(): Int {
        delay(1000)
        throw UnknownException
        return 1
    }
}
