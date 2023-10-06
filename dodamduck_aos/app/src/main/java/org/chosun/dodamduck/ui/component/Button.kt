package org.chosun.dodamduck.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.chosun.dodamduck.ui.theme.Brown

@Composable
fun PrimaryButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(55.dp)
        .padding(start = 32.dp, end = 32.dp)
        .shadow(elevation = 12.dp, shape = RoundedCornerShape(12.dp)),
    text: String = ""
) {
    Button (
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Brown,
            contentColor = Color.White
        )
    ) {
        Text(text = text,
             fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPrimaryButton() {
    PrimaryButton()
}