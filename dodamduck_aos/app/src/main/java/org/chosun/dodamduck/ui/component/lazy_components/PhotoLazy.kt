package org.chosun.dodamduck.ui.component.lazy_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.component.BackgroundText
import org.chosun.dodamduck.ui.component.GrayRoundedBox
import org.chosun.dodamduck.ui.theme.BrownOpacity80
import org.chosun.dodamduck.ui.theme.Gray8

@Composable
fun PhotoThumbnail(
    modifier: Modifier,
    painter: Painter,
    representative: Boolean = false
) {
    GrayRoundedBox(
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painter,
                contentDescription = stringResource(R.string.duck_icon),
                contentScale = ContentScale.Fit
            )

            if (representative) {
                BackgroundText(
                    text = "대표 사진",
                    modifier = Modifier.height(20.dp).align(Alignment.BottomCenter),
                    backgroundColor = BrownOpacity80,
                    contentAlignment = Alignment.Center,
                    fontSize = 9,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun PhotoSelectionList(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        PhotoCountBox(2)

        LazyRow {
            items(3) {
                PhotoThumbnail(
                    modifier = Modifier.padding(start = 8.dp),
                    painter = painterResource(id = R.drawable.ic_duck),
                    representative = it == 0
                )
            }
        }
    }
}

@Composable
fun PhotoCountBox(count: Int = 0) {
    GrayRoundedBox {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.padding(top = 4.dp),
                painter = painterResource(id = R.drawable.ic_camera_20),
                contentDescription = "Camera Icon",
                tint = Gray8
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "$count/10", fontSize = 7.sp, color = Gray8
            )
        }
    }
}
