package org.chosun.dodamduck.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.component.AuthBody
import org.chosun.dodamduck.ui.component.AuthTopSurface
import org.chosun.dodamduck.ui.navigation.BottomNavItem
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.Primary


@Composable
fun LoginScreen(navController: NavHostController) {
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
                buttonAction = {navController.navigate(BottomNavItem.Home.screenRoute)},
                bottomTextAction = {navController.navigate(BottomNavItem.Register.screenRoute)}
            )
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    DodamDuckTheme {
        LoginScreen(rememberNavController())
    }
}