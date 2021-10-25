package com.spamerl.pokedex_compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spamerl.pokedex_compose.domain.model.PokemonModel

sealed class NavScreens(val route: String) {
    object MAIN: NavScreens("main")
    object DETAIL: NavScreens("detail")
}

@Composable
fun NavGraph(startDestination: NavScreens = NavScreens.MAIN) {
    val navController = rememberNavController()
    val action = remember(navController) {MainActions(navController = navController) }
    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(
            route = NavScreens.MAIN.route
        ) {}
    }
}

@Composable
fun NavScreen(
    actions: MainActions
) {
    ListScreen(

    )
}

class MainActions(navController: NavHostController) {
    val moveDetail: (PokemonModel) -> Unit = {pokemon ->
        navController.navigate("${NavScreens.DETAIL.route}/${pokemon.id}")
    }
}