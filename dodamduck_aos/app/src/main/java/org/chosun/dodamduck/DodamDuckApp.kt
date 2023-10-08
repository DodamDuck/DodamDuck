package org.chosun.dodamduck

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.chosun.dodamduck.ui.navigation.DodamDuckBottomNavigation
import org.chosun.dodamduck.ui.navigation.daoDamDuckNavigationGraph
import org.chosun.dodamduck.ui.theme.DodamDuckTheme

@Composable
fun DodamDuckApp () {
    DodamDuckTheme {
        val navController = rememberNavController()
        Scaffold (
            bottomBar = { DodamDuckBottomNavigation(navController = navController) }
        ) {
            Box(Modifier.padding(it)) {
                daoDamDuckNavigationGraph(navController = navController)
            }
        }
    }
}

@Composable
@Preview
fun PreviewDodamDuckApp() {
    DodamDuckApp()
}