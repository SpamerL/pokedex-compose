package com.spamerl.pokedex_compose.ui.navigation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kryak.domain.model.PokemonListModel
import com.spamerl.pokedex_compose.ui.screens.details.DetailScreen
import com.spamerl.pokedex_compose.ui.screens.main_list.MainList

sealed class NavScreenRoutes(val route: String) {
    object MAIN : NavScreenRoutes("main")
    object DETAIL : NavScreenRoutes("detail")
}

@Composable
fun NavGraph(startDestination: NavScreenRoutes = NavScreenRoutes.MAIN) {
    val navController = rememberNavController()
    val action = remember(navController) { Actions(navController) }
    NavHost(navController, startDestination.route) {
        composable(
            route = NavScreenRoutes.MAIN.route
        ) { NavScreen(action) }
        composable(
            route = "${NavScreenRoutes.DETAIL.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { DetailScreen(viewModel = hiltViewModel()) }
    }
}

@Composable
fun NavScreen(
    actions: Actions
) {
    Scaffold() {
        MainList(
            viewModel = hiltViewModel(),
            select = actions.pokemonDetails
        )
    }
}

class Actions(navController: NavHostController) {
    val pokemonDetails: (PokemonListModel) -> Unit = { pokemon ->
        navController.navigate("${NavScreenRoutes.DETAIL.route}/${pokemon.id}")
    }
}
