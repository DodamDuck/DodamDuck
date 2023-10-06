package org.chosun.dodamduck.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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