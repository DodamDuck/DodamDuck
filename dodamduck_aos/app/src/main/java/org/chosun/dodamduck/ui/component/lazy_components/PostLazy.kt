package org.chosun.dodamduck.ui.component.lazy_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.component.CommentIcon
import org.chosun.dodamduck.ui.component.DodamDuckText
import org.chosun.dodamduck.ui.component.DodamDuckTextH3
import org.chosun.dodamduck.ui.theme.Brown

@Composable
fun PostItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(modifier = Modifier.weight(1f)) {
            PostType()
            DodamDuckTextH3(text = "뽀로로 놀이 기구 어떤가요?")
            DodamDuckText(
                modifier = Modifier.padding(top = 4.dp),
                text = "뽀로로 놀이 기구를 이벤트로 받았는데 ...",
                fontSize = 9,
                color = Color.Gray
            )
            DodamDuckText(
                modifier = Modifier.padding(top = 2.dp),
                text = stringResource(id = R.string.dummy_post_info_item),
                fontSize = 9,
                color = Color.Gray
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Image(
                modifier = Modifier
                    .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                    .align(Alignment.End)
                    .size(55.dp, 55.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.ic_duck),
                contentDescription = "Exchange Item"
            )

            CommentIcon(
                    modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 2.dp),
                text = "3"
            )
        }
    }
}

@Composable
fun PostType(
    text: String = "수다",
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 12.dp,
    verticalPadding: Dp = 2.dp,
    fontSize: Int = 10,
    fontWeight: FontWeight = FontWeight.Light
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(3.dp)),
        color = Brown
    ) {
        DodamDuckText(
            modifier = Modifier.padding(horizontal = horizontalPadding, verticalPadding),
            text = text,
            color = Color.White,
            fontWeight = fontWeight,
            fontSize = fontSize
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PostItemPreview() {
    Box(Modifier.fillMaxSize()) {
        LazyColumn() {
            items(5) {
                PostItem()
            }
        }
    }
}