package org.chosun.dodamduck.presentation.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import org.chosun.dodamduck.data.model.DodamDuckData
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.DodamDuckTheme

@Composable
fun UserScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally
        ){
            Text(
                modifier = Modifier.padding(top = 40.dp),
                text = "프로필",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )

            Image(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(60.dp))
                    .size(120.dp, 120.dp)
                    .clip(RoundedCornerShape(60.dp)),
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(DodamDuckData.userInfo.profileUrl),
                contentDescription = "UserProfile"
            )

            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = DodamDuckData.userInfo.userName,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            ProfileMenuLazyColumn(Modifier.padding(horizontal = 12.dp, vertical = 30.dp))
        }
    }
}

@Preview
@Composable
fun ProfileMenuLazyColumn(modifier: Modifier = Modifier) {
    val list = listOf(
        Pair("프로필 정보", Icons.Outlined.Person),
        Pair("결제 방법", Icons.Outlined.ShoppingCart),
        Pair("주문 정보", Icons.Default.List),
        Pair("설정", Icons.Outlined.Settings),
        Pair("고객 센터", Icons.Outlined.Info),
        Pair("개인 정보 보호 정책", Icons.Outlined.AccountCircle),
        Pair("친구 초대", Icons.Outlined.Face),
        Pair("로그 아웃", Icons.Outlined.ExitToApp)
    )

    LazyColumn (
        modifier = modifier
    ) {
        items(list.size) {
            ProfileMenuItem(
                item = list[it]
            )
            if(it < list.size -1)
                Divider(Modifier.padding(vertical = 10.dp))
        }
    }
}

@Composable
fun ProfileMenuItem(
    item: Pair<String, ImageVector>
) {
    Row {
        Icon(
            modifier = Modifier
                .size(26.dp)
                .padding(end = 8.dp)
                .align(CenterVertically),
            imageVector = item.second,
            contentDescription = "menuItem Vector",
            tint = Brown
        )
        Text(text = item.first, fontSize = 20.sp)
        Spacer(Modifier.weight(1f))
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Right Arrow Vector"
        )
    }
}

@Preview
@Composable
fun UserPreview() {
    DodamDuckTheme {
        UserScreen()
    }
}