package org.chosun.dodamduck.ui.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.BoardScreen
import org.chosun.dodamduck.ui.ChatScreen
import org.chosun.dodamduck.ui.HomeScreen
import org.chosun.dodamduck.ui.LibraryScreen
import org.chosun.dodamduck.ui.LoginScreen
import org.chosun.dodamduck.ui.RegisterScreen
import org.chosun.dodamduck.ui.UserScreen

sealed class BottomNavItem(
    val title: Int, val icon: Int, val screenRoute: String
) {
    object Home : BottomNavItem(R.string.home, R.drawable.ic_home_48, R.string.home.toString())
    object Login : BottomNavItem(R.string.login, R.drawable.ic_home_48, R.string.login.toString())
    object Register : BottomNavItem(R.string.register, R.drawable.ic_home_48, R.string.register.toString())

    object Library : BottomNavItem(R.string.library, R.drawable.ic_toy_48, R.string.library.toString())

    object Board : BottomNavItem(R.string.board, R.drawable.ic_board_48, R.string.board.toString())

    object Chat : BottomNavItem(R.string.chat, R.drawable.ic_chat_48, R.string.chat.toString())

    object User : BottomNavItem(R.string.user, R.drawable.ic_user_48, R.string.user.toString())
}

@Composable
fun daoDamDuckNavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomNavItem.Login.screenRoute) {
            LoginScreen()
        }
        composable(BottomNavItem.Register.screenRoute) {
            RegisterScreen()
        }
        composable(BottomNavItem.Library.screenRoute) {
            LibraryScreen()
        }
        composable(BottomNavItem.Chat.screenRoute) {
            ChatScreen()
        }
        composable(BottomNavItem.Board.screenRoute) {
            BoardScreen()
        }
        composable(BottomNavItem.User.screenRoute) {
            UserScreen()
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf<BottomNavItem>(
        BottomNavItem.Home,
        BottomNavItem.Library,
        BottomNavItem.Chat,
        BottomNavItem.Board,
        BottomNavItem.User
    )

    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color(0xFF3F414E)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title),
                        modifier = Modifier
                            .width(26.dp)
                            .height(26.dp)
                    )
                },
                label = { Text(stringResource(id = item.title), fontSize = 9.sp) },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = Gray,
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}