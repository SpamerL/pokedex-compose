package com.spamerl.pokedex_compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.spamerl.pokedex_compose.ui.navigation.NavGraph
import com.spamerl.pokedex_compose.ui.theme.Pokedex_composeTheme
import com.spamerl.pokedex_compose.util.LocalBackDispatcher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                CompositionLocalProvider(LocalBackDispatcher.provides(onBackPressedDispatcher)) {
                    NavGraph()
                }
            }
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit) {
    Pokedex_composeTheme {
        content()
    }
}
