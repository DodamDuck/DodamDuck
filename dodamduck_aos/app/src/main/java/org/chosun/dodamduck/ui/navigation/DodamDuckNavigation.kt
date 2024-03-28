package org.chosun.dodamduck.ui.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import org.chosun.dodamduck.R
import org.chosun.dodamduck.presentation.chat.ChatListScreen
import org.chosun.dodamduck.presentation.chat.ChatScreen
import org.chosun.dodamduck.presentation.post.PostScreen
import org.chosun.dodamduck.presentation.trade.TradeScreen
import org.chosun.dodamduck.presentation.library.LibraryScreen
import org.chosun.dodamduck.presentation.auth.LoginScreen
import org.chosun.dodamduck.presentation.auth.OnboardingScreen
import org.chosun.dodamduck.presentation.detail.PostDetailScreen
import org.chosun.dodamduck.presentation.post.write.PostWriteScreen
import org.chosun.dodamduck.presentation.auth.RegisterScreen
import org.chosun.dodamduck.presentation.search.SearchScreen
import org.chosun.dodamduck.presentation.trade.write.TradeWriteScreen
import org.chosun.dodamduck.presentation.user.UserScreen
import org.chosun.dodamduck.ui.theme.Brown

sealed class BottomNavItem(
    val title: Int, val icon: Int, val screenRoute: String
) {
    object Home :
        BottomNavItem(R.string.home, R.drawable.ic_home_48, R.string.home.toString())

    object TradeWrite :
        BottomNavItem(R.string.trade_write, R.drawable.ic_home_48, R.string.trade_write.toString())

    object Onboarding :
        BottomNavItem(R.string.onboarding, R.drawable.ic_home_48, R.string.onboarding.toString())

    object Register :
        BottomNavItem(R.string.register, R.drawable.ic_home_48, R.string.register.toString())

    object Login :
        BottomNavItem(R.string.login, R.drawable.ic_home_48, R.string.login.toString())

    object Library :
        BottomNavItem(R.string.library, R.drawable.ic_toy_48, R.string.library.toString())

    object Post :
        BottomNavItem(R.string.board, R.drawable.ic_board_48, R.string.board.toString())

    object PostWrite :
        BottomNavItem(R.string.post_write, R.drawable.ic_board_48, R.string.post_write.toString())

    object PostDetail :
        BottomNavItem(R.string.post_detail, R.drawable.ic_board_48, R.string.post_detail.toString())

    object ChatList :
        BottomNavItem(R.string.chat_list, R.drawable.ic_chat_48, R.string.chat_list.toString())

    object Chat :
        BottomNavItem(R.string.chat, R.drawable.ic_chat_48, R.string.chat.toString())

    object User :
        BottomNavItem(R.string.user, R.drawable.ic_user_48, R.string.user.toString())

    object Search :
        BottomNavItem(R.string.search, R.drawable.ic_home_48, R.string.search.toString())
}

@Composable
fun DoDamDuckNavigationGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(BottomNavItem.Home.screenRoute) {
            TradeScreen(navController)
        }
        composable(BottomNavItem.TradeWrite.screenRoute) {
            TradeWriteScreen(navController)
        }
        composable(BottomNavItem.Onboarding.screenRoute) {
            OnboardingScreen(navController)
        }
        composable(BottomNavItem.Register.screenRoute) {
            RegisterScreen(navController)
        }
        composable(BottomNavItem.Login.screenRoute) {
            LoginScreen(navController)
        }
        composable(BottomNavItem.Library.screenRoute) {
            LibraryScreen()
        }
        composable(BottomNavItem.ChatList.screenRoute) {
            ChatListScreen(navController)
        }
        composable(
            route = "${BottomNavItem.Chat.screenRoute}/{currentUserID}/{otherUserID}/{otherUserName}/{postImageUrl}/{postTitle}/{category}",
            arguments = listOf(
                navArgument("currentUserID") { type = NavType.StringType },
                navArgument("otherUserID") { type = NavType.StringType },
                navArgument("otherUserName") { type = NavType.StringType },
                navArgument("postImageUrl") { type = NavType.StringType },
                navArgument("postTitle") { type = NavType.StringType },
                navArgument("category") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val currentUserID = backStackEntry.arguments?.getString("currentUserID")
            val otherUserID = backStackEntry.arguments?.getString("otherUserID")
            val otherUserName = backStackEntry.arguments?.getString("otherUserName")
            val postImageUrl = backStackEntry.arguments?.getString("postImageUrl")
            val postTitle = backStackEntry.arguments?.getString("postTitle")
            val category = backStackEntry.arguments?.getString("category")

            ChatScreen(
                navController,
                currentUserID = currentUserID ?: "",
                otherUserID = otherUserID ?: "",
                otherUserName = otherUserName ?: "",
                postImageUrl = postImageUrl ?: "",
                postTitle = postTitle ?: "",
                category = category ?: ""
            )
        }
        composable(BottomNavItem.Post.screenRoute) {
            PostScreen(navController)
        }
        composable(
            route = "${BottomNavItem.PostDetail.screenRoute}/{postId}/{postType}",
            arguments = listOf(
                navArgument("postId") { type = NavType.StringType },
                navArgument("postType") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")
            val postType = backStackEntry.arguments?.getString("postType")
            PostDetailScreen(navController, postId = postId ?: "", postType = postType ?: "")
        }
        composable(BottomNavItem.PostWrite.screenRoute) {
            PostWriteScreen(navController)
        }
        composable(BottomNavItem.User.screenRoute) {
            UserScreen()
        }
        composable(BottomNavItem.Search.screenRoute) {
            SearchScreen(navController)
        }
    }
}

@Composable
fun DodamDuckBottomNavigation(navController: NavHostController) {
    val items = listOf<BottomNavItem>(
        BottomNavItem.Home,
        BottomNavItem.Library,
        BottomNavItem.ChatList,
        BottomNavItem.Post,
        BottomNavItem.User
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute != BottomNavItem.Onboarding.screenRoute
        && currentRoute != BottomNavItem.Register.screenRoute
        && currentRoute != BottomNavItem.Login.screenRoute
        && currentRoute != BottomNavItem.TradeWrite.screenRoute
        && currentRoute != BottomNavItem.PostWrite.screenRoute
        && currentRoute != BottomNavItem.PostDetail.screenRoute
    ) {
        BottomNavigation(
            backgroundColor = Color.White,
            contentColor = Color(0xFF3F414E)
        ) {
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
                    label = {
                        Text(stringResource(id = item.title), fontSize = 9.sp, color = Brown)
                    },
                    selectedContentColor = Brown,
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
}