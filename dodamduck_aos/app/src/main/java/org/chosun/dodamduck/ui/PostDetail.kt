package org.chosun.dodamduck.ui

import  androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import org.chosun.dodamduck.model.data.DodamDuckData
import org.chosun.dodamduck.model.dto.PostCommentDTO
import org.chosun.dodamduck.model.dto.PostDetailResponse
import org.chosun.dodamduck.model.repository.DummyItemFactory
import org.chosun.dodamduck.model.viewmodel.BasePostViewModel
import org.chosun.dodamduck.model.viewmodel.PostViewModel
import org.chosun.dodamduck.model.viewmodel.TradeViewModel
import org.chosun.dodamduck.ui.component.CommentIcon
import org.chosun.dodamduck.ui.component.DodamDuckMessageInputField
import org.chosun.dodamduck.ui.component.DodamDuckText
import org.chosun.dodamduck.ui.component.SpannableText
import org.chosun.dodamduck.ui.component.lazy_components.PostType
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.utils.Utils.formatDateDiff

@Composable
fun PostDetailScreen(
    navController: NavController,
    postId: String = "",
    postType: String
) {
    val viewModel: BasePostViewModel<*> = when (postType) {
        "trade" -> hiltViewModel<TradeViewModel>()
        "post" -> hiltViewModel<PostViewModel>()
        else -> throw IllegalArgumentException("Unknown post type")
    }

    val postDetail by viewModel.postDetail.collectAsState(initial = null)

    var commentText by remember { mutableStateOf("") }
    LaunchedEffect(key1 = postDetail) {
        viewModel.fetchDetail(postId)
    }

    val scrollState = rememberScrollState()

    PostDetailContent(
        navController = navController,
        postDetail = postDetail,
        commentText = commentText,
        scrollState = scrollState,
        onSendButtonClick = {
            viewModel.uploadComment(
                postId,
                DodamDuckData.userInfo!!.userID,
                commentText
            )
            commentText = ""
        },
        onTextFieldChange = { commentText = it }
    )
}

@Composable
fun PostDetailContent(
    navController: NavController,
    postDetail: PostDetailResponse?,
    commentText: String,
    scrollState: ScrollState,
    onSendButtonClick: () -> Unit,
    onTextFieldChange: (String) -> Unit = {}
) {
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
                    .clickable { navController.popBackStack() },
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back Button",
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {

                PostType(
                    modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                    horizontalPadding = 25.dp,
                    verticalPadding = 8.dp,
                    text = "${postDetail?.post?.categoryName}",
                    fontSize = 15,
                    fontWeight = FontWeight.SemiBold
                )

                PostDetailUserInfo(
                    modifier = Modifier.padding(start = 10.dp, top = 18.dp),
                    userName = postDetail?.post?.userName ?: "도담덕 유저",
                    userProfile = postDetail?.post?.profile_url
                        ?: "userProfileUrl",
                    userInfo = "${postDetail?.post?.location?.split(" ")?.get(0)} ·" +
                            " ${postDetail?.post?.verification_count}회 · " +
                            "${postDetail?.post?.created_at?.formatDateDiff()}"
                )

                SpannableText(
                    modifier = Modifier.padding(top = 16.dp, start = 10.dp),
                    text = "T.${postDetail?.post?.title}",
                    highlightText = "T.",
                    highlightColor = Brown,
                    highlightFontSize = 28.sp,
                    defaultFontSize = 24.sp,
                    highlightFontWeight = FontWeight.SemiBold,
                    defaultFontWeight = FontWeight.Medium
                )

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(vertical = 12.dp),
                    painter = rememberAsyncImagePainter(model = postDetail?.post?.image_url),
                    contentDescription = "Post Image"
                )

                Text(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    text = postDetail?.post?.content
                        ?: stringResource(R.string.dumy_item_post_detail_content)
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                    text = "조회 ${postDetail?.post?.views}",
                    color = Color.Gray
                )

                Divider(Modifier.padding(top = 10.dp))
                CommentIcon(
                    modifier = Modifier.padding(top = 17.dp, start = 10.dp), size = 30.dp,
                    text = postDetail?.comments?.size.toString()
                )

                PostDetailComments(items = postDetail?.comments ?: listOf())
            }
            Divider()
            DodamDuckMessageInputField(
                modifier = Modifier.height(50.dp),
                onSendButtonClick = onSendButtonClick,
                onTextFieldChange = onTextFieldChange,
                value = commentText
            )
        }
    }
}

@Composable
fun PostDetailUserInfo(
    modifier: Modifier = Modifier,
    userName: String = "",
    userProfile: String = "",
    userInfo: String = "",
    postContent: String = ""
) {
    Row(modifier = modifier) {
        Image(
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(25.dp))
                .size(60.dp, 60.dp)
                .clip(RoundedCornerShape(25.dp)),
            contentScale = ContentScale.Crop,
            painter = rememberAsyncImagePainter(model = userProfile),
            contentDescription = "UserProfile"
        )

        Column(
            modifier = Modifier.padding(start = 10.dp)
        ) {
            DodamDuckText(text = userName, fontSize = 15, fontWeight = FontWeight.Bold)
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = userInfo,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(postContent, fontSize = 15.sp)
        }
    }
}

@Composable
fun PostDetailComments(items: List<PostCommentDTO>) {
    repeat(items.size) {
        val item = items[it]
        PostDetailUserInfo(
            modifier = Modifier.padding(start = 10.dp, top = 18.dp),
            userName = item.userName,
            userProfile = item.profile_url,
            userInfo = "${item.location.split(" ")[1]} · ${item.verification_count}회 · ${item.created_at.formatDateDiff()}",
            postContent = item.content
        )
    }
}

@Preview
@Composable
fun PostDetailPreview() {
    DodamDuckTheme {
        PostDetailContent(
            navController = rememberNavController(),
            postDetail = PostDetailResponse(
                DummyItemFactory.createPostDetailDto(),
                listOf(DummyItemFactory.createPostComment())
            ),
            commentText = "",
            scrollState = rememberScrollState(),
            onSendButtonClick = { /*TODO*/ }
        )
    }
}