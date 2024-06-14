package org.d3if3028.assessment1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3028.assessment1.ui.screen.DetailScreen
import org.d3if3028.assessment1.ui.screen.GaleriScreen
import org.d3if3028.assessment1.ui.screen.KEY_ID_CATATAN
import org.d3if3028.assessment1.ui.screen.ListScreen
import org.d3if3028.assessment1.ui.screen.MainScreen
import org.d3if3028.assessment1.ui.screen.ProfileScreen
import org.d3if3028.assessment1.ui.screen.SearchScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController)
        }
        composable(route = Screen.List.route) {
            ListScreen(navController)
        }
        composable(route = Screen.FormBaru.route) {
            DetailScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_CATATAN) { type = NavType.LongType }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_CATATAN)
            DetailScreen(navController, id)
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(route = Screen.Galeri.route) {
            GaleriScreen(navController)
        }
    }
}