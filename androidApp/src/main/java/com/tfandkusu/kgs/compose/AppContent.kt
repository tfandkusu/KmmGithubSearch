package com.tfandkusu.kgs.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tfandkusu.kgs.home.HomeScreen
import com.tfandkusu.kgs.home.HomeViewModelImpl
import org.koin.androidx.compose.getViewModel

private const val HOME_PATH = "home"

@Composable
fun AppContent() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HOME_PATH) {
        composable(HOME_PATH) {
            val viewModel = getViewModel<HomeViewModelImpl>()
            HomeScreen(viewModel)
        }
    }
}
