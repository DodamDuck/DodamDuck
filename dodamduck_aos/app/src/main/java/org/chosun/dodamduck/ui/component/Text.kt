package org.chosun.dodamduck.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.KadwaBold
import org.chosun.dodamduck.ui.theme.Krona

@Composable
fun WelcomeText() {
    Text(
        text = stringResource(R.string.welcome),
        style = MaterialTheme.typography.titleMedium,
        color = Brown,
        fontFamily = Krona,
        fontSize = 45.sp
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewWelcomeText() {
    WelcomeText()
}

@Composable
fun DodamDuckTextH1(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontFamily = Krona,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun DodamDuckTextH2(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontFamily = Krona,
        fontSize = 16.sp
    )
}

@Composable
fun DodamDuckTextH3(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontFamily = Krona,
        fontSize = 14.sp
    )
}

@Composable
fun DodamDuckTextT1(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontFamily = KadwaBold,
        fontSize = 20.sp
    )
}