package com.tfandkusu.kgs.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tfandkusu.kgs.R
import com.tfandkusu.kgs.common.MyTheme
import com.tfandkusu.kgs.common.MyTopAppBar
import com.tfandkusu.kgs.feature.home.HomeEffect
import com.tfandkusu.kgs.feature.home.HomeEvent
import com.tfandkusu.kgs.feature.home.HomeState
import com.tfandkusu.kgs.feature.use
import io.github.aakira.napier.Napier
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val (state, effect, dispatch) = use(viewModel)
    Napier.d("HomeScreen state = $state")
    LaunchedEffect(Unit) {
        delay(3000)
        dispatch(HomeEvent.InputKeyword("Kotlin"))
    }
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(stringResource(R.string.home_title))
                },
            )
        },
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val viewModel = object : HomeViewModel {
        override val state: State<HomeState>
            get() = mutableStateOf(HomeState())
        override val effect: Flow<HomeEffect>
            get() = flow { }

        override fun event(event: HomeEvent) {
        }
    }

    MyTheme {
        HomeScreen(viewModel)
    }
}
