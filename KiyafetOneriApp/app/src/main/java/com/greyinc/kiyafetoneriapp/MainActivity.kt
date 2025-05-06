package com.greyinc.kiyafetoneriapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.greyinc.kiyafetoneriapp.view.WeatherScreen
import com.greyinc.kiyafetoneriapp.viewmodel.WeatherViewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiKey = "API_KEY"

        setContent {
            WeatherScreen(viewModel, apiKey)
        }
    }
}
