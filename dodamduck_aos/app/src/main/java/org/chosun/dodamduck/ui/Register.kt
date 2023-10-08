package org.chosun.dodamduck.ui

import BottomStartRoundedBox
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.component.DodamDuckCheckBox
import org.chosun.dodamduck.ui.component.DodamDuckIcon
import org.chosun.dodamduck.ui.component.DodamDuckText
import org.chosun.dodamduck.ui.component.DodamDuckTextH1
import org.chosun.dodamduck.ui.component.DodamDuckTextH2
import org.chosun.dodamduck.ui.component.DodamDuckTextH3
import org.chosun.dodamduck.ui.component.DodamDuckTextT1
import org.chosun.dodamduck.ui.component.GoogleIcon
import org.chosun.dodamduck.ui.component.LoginSurface
import org.chosun.dodamduck.ui.component.LoginTextField
import org.chosun.dodamduck.ui.component.PrimaryButton
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
            Header()
            Spacer(Modifier.height(30.dp))
            Body()
        }
    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier.wrapContentSize()
    ) {
        BottomStartRoundedBox(
            modifier = Modifier.height(200.dp),
            startRound = 60
        )
        DodamDuckIcon(
            modifier = Modifier
                .offset(x = 46.dp, y = 40.dp),
            120
        )
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 175.dp, top = 40.dp)
        ) {
            DodamDuckTextH1(text = stringResource(R.string.register))
            Spacer(Modifier.height(12.dp))

            DodamDuckTextH3(text = stringResource(R.string.please_register))
        }
    }
}

@Composable
fun Body() {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LoginSurface(
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
            Spacer(Modifier.height(10.dp))

            DodamDuckTextT1(text = "or")
            Spacer(Modifier.height(12.dp))

            InputTextField()

            RegisterButtonField()
        }
    }
}

@Composable
fun InputTextField() {
    val list = listOf(
        stringResource(R.string.email),
        stringResource(R.string.create_password),
        stringResource(R.string.confirm_password)
    )
    LazyColumn(modifier = Modifier.padding(vertical = 5.dp)) {
        items(list.size) { index ->
            LoginTextField(
                label = list[index],
                width = 296,
                height = 55,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }

    DodamDuckCheckBox(
        text = stringResource(R.string.confirmed_the_terms_and_conditions),
        onCheckedChange = {},
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    )
}

@Composable
fun RegisterButtonField() {
    PrimaryButton(
        text = stringResource(id = R.string.register),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
    )

    DodamDuckText(text = "이미 계정이 있으신가요?",
        fontSize = 15,
        fontWeight = FontWeight.SemiBold,
        color = Color.DarkGray,
        modifier = Modifier.padding(top = 25.dp))
}

@Preview
@Composable
fun RegisterPreview() {
    DodamDuckTheme {
        RegisterScreen()
    }
}