package org.d3if3028.assessment1.ui.screen

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3028.assessment1.ui.theme.Assessment1Theme

@Composable
fun PublicScreen(navController: NavHostController){

}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PublicPreview() {
    Assessment1Theme {
        PublicScreen(rememberNavController())
    }
}