package com.tfandkusu.kgs.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tfandkusu.kgs.MyTheme
import com.tfandkusu.kgs.R

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
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
