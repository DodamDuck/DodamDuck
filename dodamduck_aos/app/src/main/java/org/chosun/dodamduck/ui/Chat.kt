package org.chosun.dodamduck.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import org.chosun.dodamduck.model.viewmodel.ChatViewModel
import org.chosun.dodamduck.ui.component.DodamDuckMessageInputField
import org.chosun.dodamduck.ui.component.DodamDuckTextH2
import org.chosun.dodamduck.ui.component.LikeButton
import org.chosun.dodamduck.ui.component.lazy_components.ChatLazyList
import org.chosun.dodamduck.ui.theme.DodamDuckTheme

@Composable
fun ChatScreen(
    navController: NavController,
    chatViewModel: ChatViewModel = hiltViewModel(),
    currentUser: String = "",
    otherUser: String = "",
    postImageUrl: String = "",
    postTitle: String = "",
    category: String = ""
) {
    val chats by chatViewModel.chats.collectAsState(initial = null)

    LaunchedEffect(Unit) {
        chatViewModel.getChats(currentUser, otherUser)
    }

    var message by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            ChatScreenHeader(navController)
            ChatItemInfo(
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                postTitle = postTitle.replace("+", " "),
                postImageUrl = postImageUrl,
                category = category
            )
            Divider(modifier = Modifier.padding(top = 10.dp, bottom = 5.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "2023년 9월 24일", color = Color.Gray
            )

            ChatLazyList(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .weight(1f),
                list = chats ?: listOf(),
                currentUser = currentUser,
                otherUser = otherUser
            )

            Divider()
            DodamDuckMessageInputField(
                onSendButtonClick = { chatViewModel.sendChat(currentUser, otherUser, message) },
                onTextFieldChange = { message = it },
                value = message
            )
        }
    }
}

@Composable
fun ChatScreenHeader(navController: NavController) {
    Row(
        modifier = Modifier.padding(end = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.wrapContentSize()
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back arrow Icon"
            )
        }
        Spacer(Modifier.weight(0.5f))
        DodamDuckTextH2(
            Modifier.weight(2f),
            text = "짱구 맘",
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.weight(0.7f))
    }
    Divider()
}

@Composable
fun ChatItemInfo(
    modifier: Modifier = Modifier,
    postTitle: String,
    postImageUrl: String,
    category: String
) {
    Column {
        Row(
            modifier = modifier
        ) {
            Image(
                modifier = Modifier
                    .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .size(60.dp, 60.dp),
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(postImageUrl),
                contentDescription = "Exchange Item"
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = postTitle, fontSize = 15.sp
                )
                Text(if(category == "1") "교환" else "나눔", fontSize = 15.sp)
            }
        }

        Row {
            LikeButton(
                modifier = modifier, text = "매너 칭찬하기",
                contentPaddingValues = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
            )
            LikeButton(
                modifier = modifier, text = "비매너 평가하기",
                contentPaddingValues = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                rotationScaleY = 180f
            )
        }
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    DodamDuckTheme {
        ChatScreen(rememberNavController())
    }
}