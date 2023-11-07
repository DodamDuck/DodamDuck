package org.chosun.dodamduck.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.theme.DodamDuckTheme

@Composable
fun ChatListScreen() {
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
                items(12) {
                    ChatItem()
                    Divider(modifier = Modifier.padding(top = 15.dp))
                }
            }
        }
    }
}

@Composable
fun ChatItem(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(top = 15.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(25.dp))
                .size(60.dp, 60.dp)
                .clip(RoundedCornerShape(25.dp)),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.img_user_profile),
            contentDescription = "UserProfile"
        )

        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .align(Alignment.CenterVertically),
        ) {
            Text("새삥", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Text(text = "12시는 거래가 힘들것 같네요", fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                .size(60.dp, 60.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.ic_duck),
            contentDescription = "Exchange Item"
        )
    }
}

@Preview
@Composable
fun ChatListPreview() {
    DodamDuckTheme {
        ChatListScreen()
    }
}