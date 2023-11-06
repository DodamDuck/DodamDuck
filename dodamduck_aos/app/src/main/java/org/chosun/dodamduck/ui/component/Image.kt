package org.chosun.dodamduck.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.chosun.dodamduck.R

@Composable
fun DodamDuckIcon(
    modifier: Modifier = Modifier,
    size: Int = 300
) {
    Image(
        painter = painterResource(id = R.drawable.ic_duck),
        contentDescription = stringResource(R.string.duck_icon),
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(size.dp)
    )
}

@Composable
fun GoogleIcon(
    modifier: Modifier = Modifier,
    size: Int = 30
) {
    Image(
        painter = painterResource(id = R.drawable.ic_google_30),
        contentDescription = stringResource(R.string.google_icon),
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(size.dp)
    )
}

@Composable
fun VisibleIcon(
    modifier: Modifier = Modifier,
    size: Int = 20
) {
    Image(
        painter = painterResource(id = R.drawable.ic_visible),
        contentDescription = stringResource(R.string.visible_icon),
        contentScale = ContentScale.Fit,
        modifier = modifier
            .size(size.dp)
    )
}

@Composable
fun CommentIcon(
    modifier: Modifier,
    text: String = "3",
    size: Dp = 25.dp
) {
    Row(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_comment_26),
            contentDescription = "Chat Icon",
            Modifier.size(size)
        )
        Text(
            modifier = Modifier.align(Bottom),
            fontSize = 12.sp,
            text = text,
            color = Color.Gray,
        )
    }
}