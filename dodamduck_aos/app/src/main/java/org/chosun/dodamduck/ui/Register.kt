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
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.component.AuthBody
import org.chosun.dodamduck.ui.component.AuthTopSurface
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.Primary

@Composable
fun RegisterScreen() {
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
                alreadyText = stringResource(id = R.string.already_account)
            )
        }
    }
}

@Preview
@Composable
fun RegisterPreview() {
    DodamDuckTheme {
        RegisterScreen()
    }
}