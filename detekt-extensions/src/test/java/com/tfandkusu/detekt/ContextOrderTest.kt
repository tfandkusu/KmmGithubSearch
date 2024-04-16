package com.tfandkusu.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.Test
import kotlin.test.assertEquals

class ContextOrderTest {

    private val subject = ContextOrder(Config.empty)

    @Test
    fun given_codeWithError_then_report() {
        val code = """
            fun foo(bar: String, baz: Context) {
            }
        """.trimIndent()
        val findings = subject.lint(code)
        assertEquals(1, findings.size)
        assertEquals("ContextOrder", findings[0].id)
    }

    @Test
    fun given_codeWithoutError_then_noReport() {
        val code = """
            fun foo(bar: Context, baz: String) {
            }
        """.trimIndent()
        val findings = subject.lint(code)
        assertEquals(0, findings.size)
    }
}
