package com.example.cheeseai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cheeseai.core.navigation.AppNavigation
import com.example.cheeseai.ui.theme.CheeseAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheeseAITheme {
                AppNavigation()
            }
        }
    }
}