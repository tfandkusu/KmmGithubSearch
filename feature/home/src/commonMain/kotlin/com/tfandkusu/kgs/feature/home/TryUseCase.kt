package com.tfandkusu.kgs.feature.home

import kotlinx.coroutines.delay
import kotlin.coroutines.cancellation.CancellationException

object UnknownException : Exception()

class TryUseCase {
    @Throws(
        CancellationException::class,
        UnknownException::class,
    )
    suspend fun execute(): Int {
        delay(1000)
        throw UnknownException
        return 1
    }
}
