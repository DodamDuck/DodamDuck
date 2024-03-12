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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import org.chosun.dodamduck.data.dto.PostDTO
import org.chosun.dodamduck.ui.component.CommentIcon
import org.chosun.dodamduck.ui.component.DodamDuckText
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.utils.Utils.ellipsis
import org.chosun.dodamduck.utils.Utils.formatDateDiff

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    item: PostDTO
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(modifier = Modifier.weight(6f)) {
            PostType(text = item.categoryName)
            Text(text = item.title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = item.content.ellipsis(40),
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = "${item.location} · ${item.createdAt.formatDateDiff()} · ${item.views}회",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Image(
                modifier = Modifier
                    .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                    .align(Alignment.End)
                    .clip(RoundedCornerShape(10.dp))
                    .size(55.dp, 55.dp),
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(item.imageUrl),
                contentDescription = "Exchange Item"
            )

            CommentIcon(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 2.dp),
                text = item.commentCount
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
                PostItem(
                    item = PostDTO(
                        "1", "user1",
                        "1", "뽀로로 놀이기구 어떤가요?",
                        "뽀로로 놀이 기구를 이벤트로 받았는데 ...",
                        "", "2023-11-16 22:59:38","", "1"
                    )
                )
            }
        }
    }
}