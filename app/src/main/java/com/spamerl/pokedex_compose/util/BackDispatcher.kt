package com.spamerl.pokedex_compose.util

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.*


@Composable
fun BackHandler(
    enabled: Boolean = true,
    onBack: () -> Unit
) {

    val currentOnBack by rememberUpdatedState(onBack)

    val backCallback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                currentOnBack
            }
        }
    }

    SideEffect {
        backCallback.isEnabled = enabled
    }

    val backDispathcer = LocalBackDispatcher.current

    DisposableEffect(backDispathcer) {
        backDispathcer.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }
}

internal val LocalBackDispatcher = staticCompositionLocalOf<OnBackPressedDispatcher> {
    error("No Back Dispatcher provided")
}