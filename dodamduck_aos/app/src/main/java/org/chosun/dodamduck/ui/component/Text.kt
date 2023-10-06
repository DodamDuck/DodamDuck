package org.chosun.dodamduck.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.Krona

@Composable
fun WelcomeText() {
    Text(
        text = "Welcome",
        style = MaterialTheme.typography.titleMedium,
        color = Brown,
        fontFamily = Krona,
        fontSize = 45.sp,
        modifier = Modifier
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewWelcomeText() {
    WelcomeText()
}