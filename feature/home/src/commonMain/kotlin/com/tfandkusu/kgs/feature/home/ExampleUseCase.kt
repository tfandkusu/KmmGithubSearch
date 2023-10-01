package com.tfandkusu.kgs.feature.home

import kotlinx.coroutines.delay

data class ExampleException(val code: Int) : Exception()

class ExampleUseCase {

    private val repository = ExampleRepository()

    suspend fun execute(): Int {
        return repository.get()
    }
}

class ExampleRepository {

    suspend fun get(): Int {
        delay(1000)
        // throw ExampleException(404)
        return 1
    }
}
