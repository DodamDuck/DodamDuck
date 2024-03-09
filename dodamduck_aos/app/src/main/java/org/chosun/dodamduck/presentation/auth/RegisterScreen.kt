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
import androidx.compose.runtime.collectAsState
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
import androidx.navigation.compose.rememberNavController
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.component.AuthBody
import org.chosun.dodamduck.ui.component.AuthTopSurface
import org.chosun.dodamduck.ui.navigation.BottomNavItem
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.Primary

@Composable
fun RegisterScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val isRegisterError by authViewModel.isRegisterState.collectAsState(initial = "true")

    var userID by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }
    var registerLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val checkPasswordMessage = stringResource(R.string.passwords_do_not_match_each_other)


    if (registerLoading) {
        LaunchedEffect(key1 = Unit) {
            authViewModel.registerRequest(userID, password)
        }
    }

    LaunchedEffect(key1 = isRegisterError) {
        if (isRegisterError == "false") {
            navController.navigate(BottomNavItem.Login.screenRoute)
        } else if (registerLoading) {
            registerLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        Column {
            AuthTopSurface(
                stringResource(R.string.register),
                stringResource(R.string.please_register)
            )
            Spacer(Modifier.height(30.dp))
            AuthBody(
                googleButtonText = stringResource(R.string.sign_up_for_google_account),
                labelList = listOf(
                    stringResource(R.string.email),
                    stringResource(R.string.create_password),
                    stringResource(R.string.confirm_password)
                ),
                checkBoxText = stringResource(id = R.string.confirmed_the_terms_and_conditions),
                buttonText = stringResource(id = R.string.register),
                alreadyText = stringResource(id = R.string.already_account),
                bottomTextAction = { navController.navigate(BottomNavItem.Login.screenRoute) },
                buttonAction = {
                    if(password != confirmPass)
                        Toast.makeText(context, checkPasswordMessage, Toast.LENGTH_LONG ).show()
                    else registerLoading = true
                               },
                onUserIDChange = { newUserID -> userID = newUserID },
                onPasswordChange = { newPassword -> password = newPassword },
                onConfirmChange = { newConfirm -> confirmPass = newConfirm },
                emailText = userID,
                passwordText = password,
                confirmPasswordText = confirmPass
            )
        }
    }
}

@Preview
@Composable
fun RegisterPreview() {
    DodamDuckTheme {
        RegisterScreen(rememberNavController())
    }
}