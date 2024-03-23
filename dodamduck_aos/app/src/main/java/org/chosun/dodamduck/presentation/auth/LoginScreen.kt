package org.chosun.dodamduck.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.component.AuthBody
import org.chosun.dodamduck.ui.component.AuthTopSurface
import org.chosun.dodamduck.ui.navigation.BottomNavItem
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.Primary

@Composable
fun LoginScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        authViewModel.effect.collectLatest { effect ->
            when (effect) {
                is AuthSideEffect.NavigateToHomeScreen
                -> navController.navigate(BottomNavItem.Home.screenRoute) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }

                is AuthSideEffect.NavigateToRegisterScreen
                -> navController.navigate(BottomNavItem.Register.screenRoute) {
                    popUpTo(navController.graph.startDestinationId)
                }

                is AuthSideEffect.Toast
                -> Toast.makeText(context, effect.text, Toast.LENGTH_SHORT).show()

                else -> {}
            }
        }
    }

    LoginContent(
        onButtonAction = { userId, userPassword ->
            authViewModel.loginRequest(userId, userPassword)
        },
        onBottomTextAction = { authViewModel.sendSideEffect(AuthSideEffect.NavigateToRegisterScreen) }
    )

}

@Composable
fun LoginContent(
    onButtonAction: (String, String) -> Unit,
    onBottomTextAction: () -> Unit
) {
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        Column {
            AuthTopSurface(
                stringResource(R.string.login),
                stringResource(R.string.please_login)
            )
            Spacer(Modifier.height(30.dp))
            AuthBody(
                googleButtonText = stringResource(R.string.log_in_with_google_account),
                labelList = listOf(
                    stringResource(R.string.email),
                    stringResource(R.string.password),
                ),
                checkBoxText = stringResource(R.string.did_you_forget_your_password),
                buttonText = stringResource(id = R.string.login),
                alreadyText = stringResource(R.string.dont_you_have_an_account),
                checkBoxVisible = false,
                buttonAction = { onButtonAction(userId, password) },
                bottomTextAction = { onBottomTextAction() },
                onUserIDChange = { newUserID -> userId = newUserID },
                onPasswordChange = { newPassword -> password = newPassword },
                emailText = userId,
                passwordText = password
            )
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    DodamDuckTheme {
        LoginContent(onButtonAction = { _, _ -> }, onBottomTextAction = {})
    }
}
