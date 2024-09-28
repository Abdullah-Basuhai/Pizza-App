package com.example.pizzago

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pizzago.ui.theme.AppTheme
import com.example.pizzago.ui.PizzaBuilderScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          AppTheme {
              PizzaBuilderScreen()
          }
        }
    }
}


