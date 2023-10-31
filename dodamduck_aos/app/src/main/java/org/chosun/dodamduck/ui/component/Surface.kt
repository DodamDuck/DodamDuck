package org.chosun.dodamduck.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.theme.Secondary
import org.chosun.dodamduck.ui.theme.WhiteOpacity45
import kotlin.math.ln

private const val DEFAULT_CORNER_RADIUS = 12

@Composable
fun AuthInputSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(DEFAULT_CORNER_RADIUS.dp),
    color: Color = WhiteOpacity45,
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .shadow(elevation = elevation, shape = shape, clip = false)
            .background(
                color = getBackgroundColorForElevation(color, elevation),
                shape = shape
            )
            .border(2.dp, Color.Black, shape)
    ) {
        content()
    }
}

@Composable
@Preview
fun PreviewAuthInputSurface() {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(Secondary)
    ) {
        AuthInputSurface(
            modifier = Modifier
                .width(296.dp)
                .height(55.dp)
                .align(alignment = Alignment.Center)
        )
    }
}

@Composable
fun AuthTopSurface(
    title: String = "",
    subTitle: String = ""
) {
    Box(
        modifier = Modifier.wrapContentSize()
    ) {
        BottomRoundedBox(
            modifier = Modifier.height(200.dp),
            startRound = 60
        )
        DodamDuckIcon(
            modifier = Modifier
                .offset(x = 46.dp, y = 40.dp),
            120
        )
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 175.dp, top = 40.dp)
        ) {
            DodamDuckTextH1(text = title)
            Spacer(Modifier.height(12.dp))
            DodamDuckTextH3(text = subTitle)
        }
    }
}

@Composable
@Preview
fun PreviewAuthTopSurface() {
    AuthTopSurface(stringResource(R.string.register), stringResource(R.string.please_register))
}


@Composable
private fun getBackgroundColorForElevation(color: Color, elevation: Dp): Color {
    return if (elevation > 0.dp) {
        color.withElevation(elevation)
    } else {
        color
    }
}

private fun Color.withElevation(elevation: Dp): Color {
    val foreground = calculateForeground(elevation)
    return foreground.compositeOver(this)
}

private fun calculateForeground(elevation: Dp): Color {
    val alpha = ((4.5f * ln(elevation.value + 1)) + 2f) / 100f
    return Color.White.copy(alpha = alpha)
}