package org.chosun.dodamduck.ui

import BottomStartRoundedBox
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.component.DodamDuckIcon
import org.chosun.dodamduck.ui.component.DodamDuckTextH1
import org.chosun.dodamduck.ui.component.DodamDuckTextH3
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.Primary

@Composable
fun RegisterScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        Header()
    }
}

@Composable
fun Header() {
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

@Preview
@Composable
fun RegisterPreview() {
    DodamDuckTheme {
        RegisterScreen()
    }
}