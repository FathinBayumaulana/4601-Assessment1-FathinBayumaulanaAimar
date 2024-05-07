package org.d3if3028.assessment1.navigation

import org.d3if3028.assessment1.ui.screen.KEY_ID_CATATAN

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object List: Screen("listScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_CATATAN}") {
        fun withId(id: Long?) = "detailScreen/$id"
    }
}