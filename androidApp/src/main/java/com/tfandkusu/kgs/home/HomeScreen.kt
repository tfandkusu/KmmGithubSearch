package com.tfandkusu.kgs.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tfandkusu.kgs.R
import com.tfandkusu.kgs.compose.MyTheme
import com.tfandkusu.kgs.compose.MyTopAppBar
import com.tfandkusu.kgs.feature.home.HomeEffect
import com.tfandkusu.kgs.feature.home.HomeEvent
import com.tfandkusu.kgs.feature.home.HomeState
import com.tfandkusu.kgs.feature.use
import com.tfandkusu.kgs.model.GithubRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val (state, effect, dispatch) = use(viewModel)
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    LaunchedEffect(Unit) {
        effect.collect {
            when (it) {
                HomeEffect.HideKeyboard -> {
                    focusManager.clearFocus()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(stringResource(R.string.home_title))
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .imePadding(),
        ) {
            Box(modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp),
                            shape = RoundedCornerShape(16.dp),
                        ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.home_search_content_description),
                        )
                    },
                    placeholder = {
                        Text(text = stringResource(R.string.home_search_placeholder))
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            dispatch(HomeEvent.SearchKeyword(state.keyword))
                        },
                    ),
                    value = state.keyword,
                    onValueChange = {
                        dispatch(HomeEvent.InputKeyword(it))
                    },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        containerColor = Color.Transparent,
                    ),
                )
            }
            Divider()
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                items(
                    count = state.items.size,
                    key = { index ->
                        when (val item = state.items[index]) {
                            HomeState.Item.NetworkError -> HomeItemKey.NetworkError
                            HomeState.Item.Progress -> HomeItemKey.Progress
                            is HomeState.Item.Repo -> HomeItemKey.Repo(item.repo.id)
                            is HomeState.Item.ServerError -> HomeItemKey.ServerError
                        }
                    },
                    contentType = { index ->
                        when (state.items[index]) {
                            HomeState.Item.NetworkError -> 1
                            HomeState.Item.Progress -> 2
                            is HomeState.Item.Repo -> 3
                            is HomeState.Item.ServerError -> 4
                        }
                    },
                ) { index ->
                    val item = state.items[index]
                    HomeItem(state = item)
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val repos = listOf(
        GithubRepo(
            517191221,
            "DroidKaigi/conference-app-2022",
            "DroidKaigi",
            "https://avatars.githubusercontent.com/u/10727543?v=4",
            "Kotlin",
            460,
            460,
            194,
            39,
        ),
        GithubRepo(
            283062475,
            "DroidKaigi/conference-app-2021",
            "DroidKaigi",
            "https://avatars.githubusercontent.com/u/10727543?v=4",
            "Kotlin",
            632,
            632,
            189,
            45,
        ),
        GithubRepo(
            202978106,
            "DroidKaigi/conference-app-2020",
            "DroidKaigi",
            "https://avatars.githubusercontent.com/u/10727543?v=4",
            "Kotlin",
            785,
            785,
            330,
            46,
        ),
    )
    val state = HomeState(
        items = repos.map {
            HomeState.Item.Repo(it)
        },
    )
    val viewModel = object : HomeViewModel {
        override val state: State<HomeState>
            get() = mutableStateOf(state)
        override val effect: Flow<HomeEffect>
            get() = flow { }

        override fun event(event: HomeEvent) {
        }
    }

    MyTheme {
        HomeScreen(viewModel)
    }
}
