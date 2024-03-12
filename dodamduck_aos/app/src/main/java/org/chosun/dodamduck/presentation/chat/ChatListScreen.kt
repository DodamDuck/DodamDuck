package org.chosun.dodamduck.presentation.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import org.chosun.dodamduck.R
import org.chosun.dodamduck.data.model.DodamDuckData
import org.chosun.dodamduck.data.dto.ChatListDTO
import org.chosun.dodamduck.ui.navigation.BottomNavItem
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.utils.Utils.ellipsis
import org.chosun.dodamduck.utils.Utils.getUserProfileUrl
import java.net.URLEncoder

@Composable
fun ChatListScreen(
    navController: NavController,
    chatViewModel: ChatViewModel = hiltViewModel()
) {
    val currentUser = DodamDuckData.userInfo.userID
    val chats by chatViewModel.chatList.collectAsState(initial = null)

    LaunchedEffect(Unit) {
        chatViewModel.getChatList(currentUser)
    }

    ChatListContent(
        navController = navController,
        chats = chats ?: listOf(),
        currentUser = currentUser
    )

}

@Composable
fun ChatListContent(
    navController: NavController,
    chats: List<ChatListDTO>,
    currentUser: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(top = 15.dp, start = 11.dp, end = 11.dp)
        ) {
            Text(
                text = stringResource(id = R.string.chat),
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
            )

            LazyColumn {
                chats.let {
                    items(it.size) { index ->
                        ChatItem(
                            navController = navController,
                            item = it[index],
                            seller = if (currentUser == it[index].user1_id) it[index].user2_name else it[index].user1_name,
                            postTitle = it[index].post_title,
                            postImageUrl = it[index].post_image_url
                        )
                        Divider(modifier = Modifier.padding(top = 15.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ChatItem(
    modifier: Modifier = Modifier,
    navController: NavController,
    item: ChatListDTO,
    seller: String,
    postTitle: String,
    postImageUrl: String
) {
    val imageUrl = URLEncoder.encode(postImageUrl, "UTF-8")
    val title = URLEncoder.encode(postTitle, "UTF-8")

    val currentUserID = if(DodamDuckData.userInfo.userID == item.user1_id) item.user1_id else item.user2_id
    val otherUserID = if(DodamDuckData.userInfo.userID == item.user1_id) item.user2_id else item.user1_id
    val otherUserName = if(DodamDuckData.userInfo.userID == item.user1_id) item.user2_name else item.user1_name
    Row(
        modifier = modifier
            .padding(top = 15.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    "${BottomNavItem.Chat.screenRoute}/${currentUserID}/${otherUserID}/${otherUserName}/${imageUrl}/${title}/${item.category}"
                )
            }
    ) {
        Image(
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(25.dp))
                .size(60.dp, 60.dp)
                .clip(RoundedCornerShape(25.dp)),
            contentScale = ContentScale.Crop,
            painter = rememberAsyncImagePainter(model = otherUserID.getUserProfileUrl()),
            contentDescription = "UserProfile"
        )

        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .align(Alignment.CenterVertically),
        ) {
            Text(seller, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Text(text = item.last_message.ellipsis(17), fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .size(60.dp, 60.dp),
            contentScale = ContentScale.Crop,
            painter = rememberAsyncImagePainter(model = item.post_image_url),
            contentDescription = "Exchange Item"
        )
    }
}

@Preview
@Composable
fun ChatListPreview() {
    DodamDuckTheme {
        ChatListContent(
            navController = rememberNavController(),
            chats = listOf(
                ChatListDTO(
                    "1",
                    "user1",
                    "user2",
                    "홀길동",
                    "전지현",
                    "안녕하십니까",
                    "2023-11-29 22:38:53",
                    "인형 교환할 분 구합니다",
                    "",
                    "",
                    ""
                )
            ),
            currentUser = "user1"
        )
    }
}