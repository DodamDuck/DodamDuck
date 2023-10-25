package org.chosun.dodamduck.ui

import BottomRoundedBox
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.chosun.dodamduck.ui.component.DodamDuckIcon
import org.chosun.dodamduck.ui.component.DodamDuckTextH2
import org.chosun.dodamduck.ui.component.DodamDuckTitleText
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.Primary

@Composable
fun LibraryScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        BottomRoundedBox(
            modifier = Modifier.height(200.dp),
            startRound = 40
        ){
            DodamDuckTextH2(
                modifier = Modifier.align(Alignment.Center).padding(top = 10.dp),
                text = "찾으시는 장난감이 있으신가요?",
                color = Brown,
                fontWeight = FontWeight.ExtraBold
            )
        }

        DodamDuckTitleText(modifier = Modifier.padding(start = 12.dp, top = 20.dp))
        DodamDuckIcon(modifier = Modifier.padding(start = 134.dp, top = 20.dp), size = 46)
    }
}

@Preview
@Composable
fun LibraryPreview() {
    DodamDuckTheme {
        LibraryScreen()
    }
}