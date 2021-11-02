package com.spamerl.pokedex_compose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.spamerl.pokedex_compose.data.remote.model.CombinedListItem
import com.spamerl.pokedex_compose.ui.screens.detail.DetailScreen
import com.spamerl.pokedex_compose.ui.screens.mainList.ListScreen

sealed class NavScreens(val route: String) {
    object MAIN: NavScreens("main")
    object DETAIL: NavScreens("detail")
}


@ExperimentalFoundationApi
@Composable
fun NavGraph(startDestination: NavScreens = NavScreens.MAIN) {
    val navController = rememberNavController()
    val action = remember(navController) {MainActions(navController = navController) }
    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(
            route = NavScreens.MAIN.route
        ) { NavScreen(actions = action)}
        composable(
            route = "${NavScreens.DETAIL.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType})
        ) { DetailScreen(hiltViewModel())}
    }
}


@ExperimentalFoundationApi
@Composable
fun NavScreen(
    actions: MainActions
) {
    Scaffold {
        ListScreen(
            viewModel = hiltViewModel(),
            actions.moveDetail
        )
    }
}

class MainActions(navController: NavHostController) {
    val moveDetail: (CombinedListItem) -> Unit = { pokemon ->
        navController.navigate("${NavScreens.DETAIL.route}/${pokemon.id}")
    }
}