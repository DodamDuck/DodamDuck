package org.chosun.dodamduck.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.Gugi
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
fun DodamDuckTitleText(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.dodam_duck),
        color = Brown,
        fontFamily = Gugi,
        style = TextStyle(
            fontSize = 40.sp,
            shadow = Shadow(
                color = Color.Gray, offset = Offset(5f, 3f), blurRadius = 5f
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDodamDuckTitleText() {
    DodamDuckTitleText()
}

@Composable
fun DodamDuckText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    fontSize: Int,
    fontWeight: FontWeight = FontWeight.Medium,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontFamily = Krona,
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
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
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Medium,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontFamily = Krona,
        fontSize = 16.sp,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
}

@Composable
fun DodamDuckTextH3(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Light,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontFamily = Krona,
        fontSize = 14.sp,
        fontWeight = fontWeight,
        textAlign = textAlign
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

@Composable
fun EllipsisText(text: String, maxLength: Int) {
    val displayText = if (text.length > maxLength) {
        text.take(maxLength - 3) + "..."
    } else {
        text
    }

    Text(text = displayText)
}

@Composable
fun BackgroundText(
    modifier: Modifier,
    text: String,
    backgroundColor: Color,
    contentAlignment: Alignment = Alignment.Center,
    textAlign: TextAlign? = null,
    fontSize: Int,
    color: Color
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                backgroundColor,
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            ),
        contentAlignment = contentAlignment
    ) {
        Text(
            text = text,
            color = color,
            fontSize = fontSize.sp,
            textAlign = textAlign
        )
    }
}