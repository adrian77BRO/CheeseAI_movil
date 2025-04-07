package com.example.cheeseai.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cheeseai.home.presentation.view.IntroScreen
import com.example.cheeseai.home.presentation.view.PredictScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationMenu(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "intro",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("intro") {
                IntroScreen(navController = navController)
            }
            composable("predict") {
                PredictScreen(viewModel = viewModel())
            }
        }
    }
}