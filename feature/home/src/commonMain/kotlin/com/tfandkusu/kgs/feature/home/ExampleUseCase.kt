package com.tfandkusu.kgs.feature.home

import kotlinx.coroutines.delay

data class ExampleException(val code: Int) : Exception()

class ExampleUseCase {

    private val repository = ExampleRepository()

    /**
     * iOS 側から UseCase メソッドを呼ぶ実験
     *
     * KMP-NativeCoroutines を使う場合は、Throws アノテーションは無くても良い
     */
    // @NativeCoroutines
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
