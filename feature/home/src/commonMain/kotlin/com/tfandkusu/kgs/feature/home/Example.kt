package com.tfandkusu.kgs.feature.home

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

suspend fun exampleFunction() {
    delay(3000)
}


private val exampleMutableStateFlow = MutableStateFlow(0)
fun getExampleFlow(): Flow<Int> {
    return exampleMutableStateFlow
}

fun countUpExampleFlowValue() {
    ++exampleMutableStateFlow.value
}