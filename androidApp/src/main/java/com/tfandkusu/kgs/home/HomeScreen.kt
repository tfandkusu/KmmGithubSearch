package com.tfandkusu.kgs.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tfandkusu.kgs.R
import com.tfandkusu.kgs.common.MyTheme
import com.tfandkusu.kgs.common.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
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
    MyTheme {
        HomeScreen()
    }
}
