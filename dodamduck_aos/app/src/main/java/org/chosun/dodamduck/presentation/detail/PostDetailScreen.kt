package org.chosun.dodamduck.presentation.detail

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import org.chosun.dodamduck.R
import org.chosun.dodamduck.data.model.DodamDuckData
import org.chosun.dodamduck.data.dto.PostCommentDTO
import org.chosun.dodamduck.data.dto.PostDetailResponse
import org.chosun.dodamduck.data.repository.DummyItemFactory
import org.chosun.dodamduck.presentation.detail.base.BasePostDetailViewModel
import org.chosun.dodamduck.presentation.detail.base.PostDetailSideEffect
import org.chosun.dodamduck.presentation.detail.post.PostDetailViewModel
import org.chosun.dodamduck.presentation.detail.trade.TradeDetailViewModel
import org.chosun.dodamduck.ui.component.BottomSheet
import org.chosun.dodamduck.ui.component.BottomSheetText
import org.chosun.dodamduck.ui.component.CommentIcon
import org.chosun.dodamduck.ui.component.DodamDuckMessageInputField
import org.chosun.dodamduck.ui.component.DodamDuckText
import org.chosun.dodamduck.ui.component.SpannableText
import org.chosun.dodamduck.ui.component.lazy_components.PostType
import org.chosun.dodamduck.ui.navigation.BottomNavItem
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.utils.Utils.formatDateDiff

@Composable
fun PostDetailScreen(
    navController: NavController,
    postId: String = "",
    postType: String,
    context: Context = LocalContext.current
) {
    val postDetailViewModel: BasePostDetailViewModel<*> = when (postType) {
        "trade" -> hiltViewModel<TradeDetailViewModel>()
        "post" -> hiltViewModel<PostDetailViewModel>()
        else -> throw IllegalArgumentException("Unknown post type")
    }

    val state by postDetailViewModel.uiState.collectAsStateWithLifecycle()
    val effect by postDetailViewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    var commentText by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        postDetailViewModel.fetchDetail(postId)
    }

    LaunchedEffect(key1 = effect) {
        when(effect) {
            is PostDetailSideEffect.NavigateToChatList
            -> navController.navigate(BottomNavItem.ChatList.screenRoute)

            is PostDetailSideEffect.Toast
            -> Toast.makeText(context, (effect as PostDetailSideEffect.Toast).text, Toast.LENGTH_SHORT).show()

            is PostDetailSideEffect.NavigatePopBackStack
            -> navController.popBackStack()

            else -> {}
        }
    }

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = state.postDetail) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    PostDetailContent(
        navController = navController,
        postDetail = state.postDetail,
        commentText = commentText,
        scrollState = scrollState,
        onSendButtonClick = {
            coroutineScope.launch {
                postDetailViewModel.uploadComment(
                    postId,
                    DodamDuckData.userInfo.userID,
                    commentText
                )
                commentText = ""
                focusManager.clearFocus()
            }
        },
        onTextFieldChange = { commentText = it },
        onDelete = { postDetailViewModel.deletePost(postId, DodamDuckData.userInfo.userID) },
        onChat = { postDetailViewModel.createChat(postId, DodamDuckData.userInfo.userID) },
        postType = postType
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailContent(
    navController: NavController,
    postDetail: PostDetailResponse?,
    commentText: String,
    scrollState: ScrollState,
    onSendButtonClick: () -> Unit,
    onTextFieldChange: (String) -> Unit = {},
    onDelete: () -> Unit = {},
    onChat: () -> Unit = {},
    postType: String
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

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
                    .clickable {
                        if (postType == "trade")
                            navController.navigate(BottomNavItem.Home.screenRoute)
                        else if (postType == "post")
                            navController.navigate(BottomNavItem.Post.screenRoute)
                    },
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back Button",
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    PostType(
                        modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                        horizontalPadding = 25.dp,
                        verticalPadding = 8.dp,
                        text = "${postDetail?.post?.categoryName}",
                        fontSize = 15,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier
                            .align(Bottom)
                            .size(30.dp)
                            .clickable { showBottomSheet = true },
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More Icon"
                    )
                }

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
                    modifier = Modifier.padding(top = 16.dp, start = 10.dp, bottom = 12.dp),
                    text = "T.${postDetail?.post?.title}",
                    highlightText = "T.",
                    highlightColor = Brown,
                    highlightFontSize = 28.sp,
                    defaultFontSize = 24.sp,
                    highlightFontWeight = FontWeight.SemiBold,
                    defaultFontWeight = FontWeight.Medium
                )

                Image(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(25.dp))
                        .aspectRatio(5f / 5f),
                    painter = rememberAsyncImagePainter(model = postDetail?.post?.image_url),
                    contentDescription = "Post Image"
                )

                Text(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 12.dp),
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

    val bottomSheetClose = {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                showBottomSheet = false
            }
        }
    }

    if (showBottomSheet) {
        BottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            PostDetailBottomSheetContent(
                onClose = { bottomSheetClose() },
                onDelete = onDelete,
                postWriterID = postDetail?.post?.user_id,
                onChat = { onChat() }
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

@Composable
fun PostDetailBottomSheetContent(
    onClose: () -> Unit,
    onDelete: () -> Unit,
    onChat: () -> Unit,
    postWriterID: String?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        if (postWriterID == DodamDuckData.userInfo.userID) {
            BottomSheetText(text = "수정")
            Divider(modifier = Modifier.padding(vertical = 12.dp))
            BottomSheetText(
                modifier = Modifier.clickable(onClick = onDelete),
                text = "삭제", color = Color.Red
            )
        } else {
            BottomSheetText(
                modifier = Modifier.clickable(onClick = onChat),
                text = "채팅하기"
            )
        }
        Divider(modifier = Modifier.padding(vertical = 12.dp))
        BottomSheetText(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .clickable(onClick = onClose),
            "닫기"
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
            onSendButtonClick = { /*TODO*/ },
            postType = ""
        )
    }
}