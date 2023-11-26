package kgs

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Assert
import org.junit.Test

class ArchitectureTest {
    @Test
    fun checkAllRemoteDataSourceImplsAreInternal() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("RemoteDataSourceImpl")
            .assertTrue { it.hasInternalModifier }
    }

    @Test
    fun checkAllActionCreatorsAreRegisteredInHelper() {
        val registeredClassNames = Konsist.scopeFromProject().classes()
            .withNameEndingWith("ViewModelHelper")
            .flatMap {
                it.properties()
            }.map {
                it.type?.name ?: ""
            }.toSet()
        val actionCreators = Konsist.scopeFromProject().classes()
            .withNameEndingWith("ActionCreator")
        val reducers = Konsist.scopeFromProject().classes()
            .withNameEndingWith("Reducer")
        val checkClassNames = (actionCreators + reducers).map {
            it.name
        }.toSet()
        Assert.assertEquals(checkClassNames, registeredClassNames)
    }
}
