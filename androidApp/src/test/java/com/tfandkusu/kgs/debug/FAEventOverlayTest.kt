package com.tfandkusu.kgs.debug

import androidx.activity.ComponentActivity
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withParentOf
import junit.framework.TestCase.assertTrue
import org.junit.Test

class FAEventOverlayTest {
    @Test
    fun checkAllActivitiesImplementFAEventOverlay() {
        Konsist.scopeFromProject()
            .classes()
            .withParentOf(ComponentActivity::class)
            .map { activity ->
                assertTrue(
                    "すべての Activity は FAEventOverlayHelper を使う必要がある",
                    activity.hasProperty {
                        it.hasTypeOf(AnalyticsEventOverlayHelper::class)
                    },
                )
            }
    }
}
