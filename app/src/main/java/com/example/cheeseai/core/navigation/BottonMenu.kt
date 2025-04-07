package com.example.cheeseai.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationMenu(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(containerColor = Color(0xFF121212)) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = currentRoute == "intro",
            onClick = {
                if (currentRoute != "intro") {
                    navController.navigate("intro") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Yellow,
                selectedTextColor = Color.Yellow,
                indicatorColor = Color.DarkGray,
                unselectedIconColor = Color.LightGray,
                unselectedTextColor = Color.LightGray
            )
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = "Predecir") },
            label = { Text("Predecir") },
            selected = currentRoute == "predict",
            onClick = {
                if (currentRoute != "predict") {
                    navController.navigate("predict") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Yellow,
                selectedTextColor = Color.Yellow,
                indicatorColor = Color.DarkGray,
                unselectedIconColor = Color.LightGray,
                unselectedTextColor = Color.LightGray
            )
        )
    }
}