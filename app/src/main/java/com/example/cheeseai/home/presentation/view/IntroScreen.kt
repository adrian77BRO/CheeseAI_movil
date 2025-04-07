package com.example.cheeseai.home.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun IntroScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¡Bienvenido a CheeseAI! 🧀",
            style = MaterialTheme.typography.headlineSmall.copy(color = Color.Yellow),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Con esta app puedes descubrir uno de los tipos de quesos más comunes en México con solo una imagen.",
            color = Color.White,
            modifier = Modifier.padding(24.dp)
        )
    }
}