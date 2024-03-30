package org.chosun.dodamduck.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.component.BottomRoundedBox
import org.chosun.dodamduck.ui.component.DodamDuckIcon
import org.chosun.dodamduck.ui.component.PrimaryButton
import org.chosun.dodamduck.ui.component.WelcomeText
import org.chosun.dodamduck.ui.navigation.Screen
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.Primary

@Composable
fun OnboardingScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            OnboardingTopArea(modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(82.dp))

            WelcomeText()
            Spacer(modifier = Modifier.height(32.dp))

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.login),
                onClick = { navController.navigate(Screen.Login.screenRoute) }
            )
            Spacer(modifier = Modifier.height(27.dp))
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.register),
                onClick = { navController.navigate(Screen.Register.screenRoute) }
            )
        }
    }
}

@Composable
fun OnboardingTopArea(modifier: Modifier) {
    BottomRoundedBox(
        modifier = Modifier.height(400.dp),
        startRound = 200
    ) {
        Box(modifier = modifier) {
            DodamDuckIcon(
                modifier = Modifier
                    .padding(top = 60.dp)
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                size = 300
            )
        }
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    DodamDuckTheme {
        OnboardingScreen(rememberNavController())
    }
}
