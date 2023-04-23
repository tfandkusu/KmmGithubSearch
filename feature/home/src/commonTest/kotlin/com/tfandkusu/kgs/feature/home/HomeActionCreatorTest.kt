package com.tfandkusu.kgs.feature.home

import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeActionCreatorTest {

    @BeforeTest
    fun setUp() {
    }

    @Test
    fun inputKeyword() = runTest {
        1 shouldBe 1
    }
}
