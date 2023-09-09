package kgs

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assert
import org.junit.Test

class ArchitectureTest {
    @Test
    fun checkAllRemoteDataSourceImplsAreInternal() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("RemoteDataSourceImpl")
            .assert { it.hasInternalModifier }
    }
}
