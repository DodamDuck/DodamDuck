package org.chosun.dodamduck.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.chosun.dodamduck.ui.component.DodamDuckTextH2
import org.chosun.dodamduck.ui.component.ExchangeItemList
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.DodamDuckTheme

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            Header()
            ExchangeItemList(modifier = Modifier.padding(top = 24.dp))
        }
    }
}

@Composable
fun Header() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp, top = 12.dp)
    ) {
        DodamDuckTextH2(text = "빛가람동", color = Brown)
        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Arrow Icon")
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
    }
}

@Preview
@Composable
fun HomePreview() {
    DodamDuckTheme {
        HomeScreen()
    }
}