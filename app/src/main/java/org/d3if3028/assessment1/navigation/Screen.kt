package org.d3if3028.assessment1.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object List: Screen("listScreen")
}