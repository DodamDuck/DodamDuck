package org.chosun.dodamduck.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import org.chosun.dodamduck.ui.theme.Secondary

@Composable
fun BottomRoundedBox(
    modifier: Modifier = Modifier,
    startRound: Int = 0,
    endRound: Int = 0,
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp, 0.dp, endRound.dp, startRound.dp))
            .background(Secondary)
    ) {
        content()
    }
}

@Composable
fun GrayRoundedBox(
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .size(size),
    ) {
        content()
    }
}

@Preview
@Composable
fun PreviewRoundedBottomBox() {
    BottomRoundedBox()
}
