package org.chosun.dodamduck.ui

import  androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.chosun.dodamduck.R
import org.chosun.dodamduck.model.repository.DummyItemFactory
import org.chosun.dodamduck.ui.component.CommentIcon
import org.chosun.dodamduck.ui.component.DodamDuckMessageInputField
import org.chosun.dodamduck.ui.component.DodamDuckText
import org.chosun.dodamduck.ui.component.SpannableText
import org.chosun.dodamduck.ui.component.lazy_components.PostType
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.DodamDuckTheme

@Composable
fun PostDetailScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(top = 8.dp)
                    .clickable {navController.popBackStack()},
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back Button",
            )

            PostType(
                modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                horizontalPadding = 25.dp,
                verticalPadding = 8.dp,
                text = "질문", fontSize = 15, fontWeight = FontWeight.SemiBold
            )

            PostDetailUserInfo(
                modifier = Modifier.padding(start = 10.dp, top = 18.dp),
                userName = "짱구 맘",
                userProfile = painterResource(id = R.drawable.img_user_profile),
                postInfo = stringResource(id = R.string.dummy_post_info_item)
            )

            SpannableText(
                modifier = Modifier.padding(top = 16.dp, start = 10.dp),
                text = "T. 요새 중2병 애들은 ...",
                highlightText = "T.",
                highlightColor = Brown,
                highlightFontSize = 28.sp,
                defaultFontSize = 24.sp,
                highlightFontWeight = FontWeight.SemiBold,
                defaultFontWeight = FontWeight.Medium
            )

            Text(
                modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp),
                text = stringResource(R.string.dumy_item_post_detail_content)
            )

            Text(
                modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                text = "조회 343",
                color = Color.Gray
            )

            Divider(Modifier.padding(top = 10.dp))
            CommentIcon(Modifier.padding(top = 17.dp, start = 10.dp), size = 30.dp)

            PostDetailComments()
            Spacer(Modifier.weight(1f))
            Divider()
            DodamDuckMessageInputField()
        }
    }
}

@Composable
fun PostDetailUserInfo(
    modifier: Modifier = Modifier,
    userName: String = "",
    userProfile: Painter,
    postInfo: String = "",
    postContent: String = ""
) {
    Row(modifier = modifier) {
        Image(
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(25.dp))
                .size(60.dp, 60.dp)
                .clip(RoundedCornerShape(25.dp)),
            contentScale = ContentScale.Crop,
            painter = userProfile,
            contentDescription = "UserProfile"
        )

        Column(
            modifier = Modifier.padding(start = 10.dp)
        ) {
            DodamDuckText(text = userName, fontSize = 15, fontWeight = FontWeight.Bold)
            DodamDuckText(
                modifier = Modifier.padding(top = 2.dp),
                text = postInfo,
                fontSize = 12,
                color = Color.Gray
            )
            Text(postContent, fontSize = 15.sp)
        }
    }
}

@Composable
fun PostDetailComments() {
    val postDetailUserInfoStates = DummyItemFactory.createPostDetailUserInfoDummyList()
    LazyColumn {
        items(postDetailUserInfoStates.size) {
            PostDetailUserInfo(
                modifier = Modifier.padding(start = 10.dp, top = 18.dp),
                userName = postDetailUserInfoStates[it].userName,
                userProfile = postDetailUserInfoStates[it].userProfile,
                postInfo = postDetailUserInfoStates[it].info,
                postContent = postDetailUserInfoStates[it].content
            )
        }
    }
}

@Preview
@Composable
fun PostDetailPreview() {
    DodamDuckTheme {
        PostDetailScreen(rememberNavController())
    }
}