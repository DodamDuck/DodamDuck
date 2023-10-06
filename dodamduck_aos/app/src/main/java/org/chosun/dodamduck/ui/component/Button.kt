package org.chosun.dodamduck.ui.component

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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.chosun.dodamduck.ui.theme.Brown

@Composable
fun PrimaryButton(
    height: Dp = 55.dp,
    elevation: Dp = 12.dp,
    startPadding: Dp = 32.dp,
    endPadding: Dp = 32.dp,
    shape: Shape = RoundedCornerShape(15.dp),
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {}
) {
    Button (
        modifier = modifier
            .height(height)
            .padding(start = startPadding, end = endPadding)
            .shadow(elevation = elevation, shape = RoundedCornerShape(12.dp)),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Brown,
            contentColor = Color.White
        ),
        onClick = onClick
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