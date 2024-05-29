package com.tfandkusu.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.Test
import kotlin.test.assertEquals

class TrackScreenEventTest {
    private val subject = TrackScreenEvent(Config.empty)

    @Test
    fun given_codeWithoutError_then_noReport() {
        val code = """
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val (state, effect, dispatch) = use(viewModel)
    TrackScreenEvent("Home")
    LaunchedEffect(Unit) { }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MyTheme {
        HomeScreen(viewModel)
    }
}
        """.trimIndent()
        val findings = subject.lint(code)
        assertEquals(0, findings.size)
    }

    @Test
    fun given_codeWithError_then_report() {
        val code = """
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val (state, effect, dispatch) = use(viewModel)
    LaunchedEffect(Unit) { }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MyTheme {
        HomeScreen(viewModel)
    }
}
"""
        val findings = subject.lint(code)
        assertEquals(1, findings.size)
        assertEquals("TrackScreenEvent", findings[0].id)
    }
}
