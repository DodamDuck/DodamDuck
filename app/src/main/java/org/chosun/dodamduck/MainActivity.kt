package org.chosun.dodamduck

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.chosun.dodamduck.ui.navigation.BottomNavigation
import org.chosun.dodamduck.ui.navigation.daoDamDuckNavigationGraph
import androidx.navigation.compose.rememberNavController
import org.chosun.dodamduck.ui.theme.DodamDuckTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DodamDuckTheme {
                MainScreenView()
            }
        }
    }
}

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        Box(Modifier.padding(it)) {
            daoDamDuckNavigationGraph(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DodamDuckTheme {
        MainScreenView()
    }
}