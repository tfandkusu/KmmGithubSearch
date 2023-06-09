package com.tfandkusu.kgs.home

import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tfandkusu.kgs.R
import com.tfandkusu.kgs.compose.MyTheme
import com.tfandkusu.kgs.compose.recomposeHighlighter
import com.tfandkusu.kgs.feature.home.HomeState
import com.tfandkusu.kgs.model.GithubRepo
import kotlinx.datetime.Instant
import kotlinx.parcelize.Parcelize

sealed class HomeItemKey : Parcelable {
    @Parcelize
    data class Repo(val id: Long) : HomeItemKey()

    @Parcelize
    object Progress : HomeItemKey()

    @Parcelize
    object NetworkError : HomeItemKey()

    @Parcelize
    object ServerError : HomeItemKey()
}

@Stable
data class HomeItemState(
    val state: HomeState.Item,
)

@Composable
fun HomeItem(itemState: HomeItemState) {
    when (val state = itemState.state) {
        HomeState.Item.Progress -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator()
            }
        }

        is HomeState.Item.Repo -> {
            Column(
                modifier = Modifier
                    .recomposeHighlighter()
                    .fillMaxWidth(),
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = state.repo.fullName,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Divider()
            }
        }

        HomeState.Item.NetworkError -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(R.string.error_network),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        is HomeState.Item.ServerError -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(R.string.error_server, state.statusCode),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeItemPreviewProgress() {
    MyTheme {
        HomeItem(
            HomeItemState(HomeState.Item.Progress),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeItemPreviewRepo() {
    val repo = GithubRepo(
        517191221,
        "DroidKaigi/conference-app-2022",
        "DroidKaigi",
        "https://avatars.githubusercontent.com/u/10727543?v=4",
        "Kotlin",
        460,
        460,
        194,
        39,
        Instant.fromEpochMilliseconds(0),
    )
    MyTheme {
        HomeItem(
            HomeItemState(
                HomeState.Item.Repo(
                    repo,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeItemPreviewNetworkError() {
    MyTheme {
        HomeItem(
            HomeItemState(HomeState.Item.NetworkError),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeItemPreviewServerError() {
    MyTheme {
        HomeItem(
            HomeItemState(HomeState.Item.ServerError(500)),
        )
    }
}
