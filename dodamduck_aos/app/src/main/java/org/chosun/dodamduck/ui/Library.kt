package org.chosun.dodamduck.ui

import BottomRoundedBox
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.chosun.dodamduck.R
import org.chosun.dodamduck.model.viewmodel.ToyLibraryViewModel
import org.chosun.dodamduck.ui.component.DodamDuckIcon
import org.chosun.dodamduck.ui.component.DodamDuckSearchBar
import org.chosun.dodamduck.ui.component.DodamDuckTextH2
import org.chosun.dodamduck.ui.component.DodamDuckTitleText
import org.chosun.dodamduck.ui.component.ToyList
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.Primary

@Composable
fun LibraryScreen(toyLibraryViewModel: ToyLibraryViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            BottomRoundedBox(
                modifier = Modifier.height(200.dp),
                startRound = 40
            ) {
                DodamDuckTitleText(modifier = Modifier.padding(start = 12.dp, top = 20.dp))
                DodamDuckIcon(modifier = Modifier.padding(start = 134.dp, top = 20.dp), size = 46)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 26.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    DodamDuckTextH2(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 7.dp),
                        text = stringResource(R.string.ask_for_toy_search),
                        color = Brown,
                        fontWeight = FontWeight.ExtraBold
                    )

                    DodamDuckSearchBar()
                }
            }
            ToyList()
        }
    }
}


@Preview
@Composable
fun LibraryPreview() {
    DodamDuckTheme {
        LibraryScreen()
    }
}

