package org.chosun.dodamduck.ui.component.lazy_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import org.chosun.dodamduck.model.dto.ChatInfo
import org.chosun.dodamduck.ui.theme.Orange
import org.chosun.dodamduck.utils.Utils.convertDateTime
import org.chosun.dodamduck.utils.Utils.getUserProfileUrl

@Composable
fun ChatUserItem(
    modifier: Modifier = Modifier,
    chatInfo: ChatInfo
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.weight(1f))
        Text(
            modifier = Modifier
                .align(Bottom)
                .padding(end = 6.dp),
            text = chatInfo.timestamp.convertDateTime(), color = Color.Gray, fontSize = 9.sp
        )
        Surface(
            color = Orange,
            shape = RoundedCornerShape(40)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                text = chatInfo.message
            )
        }
    }
}

@Composable
fun ChatPartnerItem(
    modifier: Modifier = Modifier,
    chatInfo: ChatInfo,
    otherUser: String = ""
) {
    Row(
        modifier = modifier.padding(top = 10.dp)
    ) {
        Image(
            modifier = Modifier
                .padding(end = 11.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(25.dp))
                .size(60.dp, 60.dp)
                .clip(RoundedCornerShape(25.dp)),
            contentScale = ContentScale.Crop,
            painter = rememberAsyncImagePainter(otherUser.getUserProfileUrl()),
            contentDescription = "UserProfile"
        )

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(bottom = 3.dp),
                text = if (otherUser == chatInfo.receiverID) chatInfo.receiverName else chatInfo.senderName
            )
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .widthIn(max = 250.dp),
                color = Color(0xFFEDEDED),
                shape = RoundedCornerShape(40)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                    text = chatInfo.message
                )
            }
        }

        Text(
            modifier = Modifier
                .align(Bottom)
                .padding(start = 3.dp, bottom = 6.dp),
            text = chatInfo.timestamp.convertDateTime(), color = Color.Gray, fontSize = 9.sp
        )
        Spacer(Modifier.weight(1f))
    }
}

@Composable
fun ChatLazyList(
    modifier: Modifier = Modifier,
    list: List<ChatInfo>,
    currentUser: String,
    otherUser: String
) {
    val listState = rememberLazyListState()

    LaunchedEffect(list) {
        if (list.isNotEmpty()) {
            listState.animateScrollToItem(index = list.size - 1)
        }
    }

    Row(
        modifier = modifier
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(list.size) { index ->
                if (list[index].senderID != currentUser)
                    ChatPartnerItem(
                        modifier = Modifier.padding(top = 7.dp),
                        chatInfo = list[index],
                        otherUser = otherUser
                    )
                else
                    ChatUserItem(modifier = Modifier.padding(top = 7.dp), list[index])
            }
        }
    }
}

@Composable
@Preview
fun ChatPartnerItemPreview() {
    ChatPartnerItem(
        chatInfo = ChatInfo(
            "1",
            "seyeong1",
            "seyeong2",
            "홍길동",
            "김철수",
            "봉선동, 직거래만 가능합니다~",
            "2023-11-29 22:38:22"
        )
    )
}

@Composable
@Preview
fun ChatItemPreview() {
    ChatPartnerItem(
        chatInfo = ChatInfo(
            "1",
            "seyeong1",
            "seyeong2",
            "홍길동",
            "김철수",
            "봉선동, 직거래만 가능합니다~",
            "2023-11-29 22:38:22"
        )
    )
}
