package com.tfandkusu.kgs.home

import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tfandkusu.kgs.R
import com.tfandkusu.kgs.compose.MyTheme
import com.tfandkusu.kgs.feature.home.HomeState
import com.tfandkusu.kgs.model.GithubRepo
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

@Composable
fun HomeItem(state: HomeState.Item, onClickReload: () -> Unit) {
    when (state) {
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
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.error_network),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = onClickReload) {
                    Text(text = stringResource(R.string.reload))
                }
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
            state = HomeState.Item.Progress,
            onClickReload = {},
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
    )
    MyTheme {
        HomeItem(
            state = HomeState.Item.Repo(
                repo,
            ),
            onClickReload = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeItemPreviewNetworkError() {
    MyTheme {
        HomeItem(
            state = HomeState.Item.NetworkError,
            onClickReload = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeItemPreviewServerError() {
    MyTheme {
        HomeItem(
            state = HomeState.Item.ServerError(500),
            onClickReload = {},
        )
    }
}
