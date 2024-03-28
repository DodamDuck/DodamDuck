package org.chosun.dodamduck

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import org.chosun.dodamduck.network.auth.TokenManager
import org.chosun.dodamduck.presentation.auth.AuthSideEffect
import org.chosun.dodamduck.presentation.auth.AuthViewModel
import org.chosun.dodamduck.presentation.common.LoadingLottieScreen
import org.chosun.dodamduck.ui.navigation.BottomNavItem
import org.chosun.dodamduck.ui.navigation.DodamDuckBottomNavigation
import org.chosun.dodamduck.ui.navigation.DoDamDuckNavigationGraph
import org.chosun.dodamduck.ui.theme.DodamDuckTheme

@Composable
fun DodamDuckApp(
    tokenManager: TokenManager? = null,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    var accessToken: String?
    val navController = rememberNavController()

    val state by authViewModel.uiState.collectAsStateWithLifecycle()
    var startDestination by rememberSaveable { mutableStateOf(BottomNavItem.Onboarding.screenRoute) }

    LaunchedEffect(Unit) {
        accessToken = tokenManager?.accessToken?.first()
        accessToken?.let { authViewModel.loginRequest("", "", loginCheckSkip = true) }

        authViewModel.effect.collectLatest { effect ->
            when(effect) {
                AuthSideEffect.NavigateToHomeScreen -> {
                    startDestination = BottomNavItem.Home.screenRoute
                }
                else -> {}
            }
        }
    }

    when {
        state.isLoginLoading -> {
            LoadingLottieScreen()
        }
        else -> {
            DodamDuckTheme {
                Scaffold(
                    bottomBar = { DodamDuckBottomNavigation(navController = navController) }
                ) {
                    Box(Modifier.padding(it)) {
                        DoDamDuckNavigationGraph(
                            navController = navController,
                            startDestination = startDestination
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewDodamDuckApp() {
    DodamDuckApp()
}