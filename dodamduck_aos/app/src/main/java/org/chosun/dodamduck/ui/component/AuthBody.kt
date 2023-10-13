package org.chosun.dodamduck.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.theme.Primary

@Composable
fun AuthBody(
    googleButtonText: String,
    labelList: List<String>,
    buttonAction: () -> Unit = {},
    forgetTextAction: () -> Unit = {},
    checkBoxVisible: Boolean = true,
    checkBoxText: String,
    buttonText: String,
    alreadyText: String
) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GoogleIconField(googleButtonText)

            DodamDuckTextT1(
                text = stringResource(R.string.or),
                modifier = Modifier.padding(vertical = 10.dp)
            )

            AuthInputTextList(labelList = labelList)

            DodamDuckCheckBox(
                text = checkBoxText,
                onCheckedChange = {},
                boxVisible = checkBoxVisible,
                forgetTextAction = forgetTextAction,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )

            PrimaryButton(
                text = buttonText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
            )

            DodamDuckText(
                text = alreadyText,
                fontSize = 15,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 25.dp)
            )
        }
    }
}

@Composable
@Preview
fun PreviewAuthBody() {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(Primary)
    ) {
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

@Composable
fun GoogleIconField(googleButtonText: String) {
    AuthInputSurface(
        modifier = Modifier
            .width(296.dp)
            .height(55.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GoogleIcon(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 32.dp)
            )
            Spacer(Modifier.width(20.dp))
            DodamDuckTextH2(text = googleButtonText)
        }
    }
}

@Composable
@Preview
fun PreviewGoogleIconField() {
    AuthInputSurface(
        modifier = Modifier
            .width(296.dp)
            .height(55.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GoogleIcon(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 32.dp)
            )
            Spacer(Modifier.width(20.dp))
            DodamDuckTextH2(text = stringResource(R.string.sign_up_for_google_account))
        }
    }
}
