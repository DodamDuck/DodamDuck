package org.chosun.dodamduck.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.Gray5

@Composable
fun PrimaryButton(
    height: Dp = 55.dp,
    elevation: Dp = 12.dp,
    startPadding: Dp = 32.dp,
    endPadding: Dp = 32.dp,
    topPadding: Dp = 0.dp,
    shape: Shape = RoundedCornerShape(15.dp),
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .height(height)
            .padding(
                start = startPadding,
                end = endPadding,
                top = topPadding
            )
            .shadow(elevation = elevation, shape = RoundedCornerShape(12.dp)),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Brown,
            contentColor = Color.White
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPrimaryButton() {
    PrimaryButton()
}

@Composable
fun DodamDuckRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Button(
        modifier = modifier
            .width(90.dp)
            .height(40.dp),
        onClick = onSelect,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Brown else Color.White,
            contentColor = if (selected) Color.White else Color.Gray
        ),
        shape = RoundedCornerShape(50),
        border = if (!selected) BorderStroke(1.dp, Color.Gray) else null,
    ) {
        Text(text = text, fontSize = 11.sp)
    }
}

@Composable
fun GrayButton(modifier: Modifier = Modifier, text: String) {
    Button(
        modifier = modifier
            .width(120.dp)
            .height(40.dp),
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            containerColor = Gray5,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(8)
    ) {
        Text(text = text, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
    }
}
