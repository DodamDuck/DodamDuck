package org.chosun.dodamduck.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.chosun.dodamduck.R
import org.chosun.dodamduck.model.data.DodamDuckData
import org.chosun.dodamduck.model.viewmodel.PostViewModel
import org.chosun.dodamduck.ui.component.DodamDuckText
import org.chosun.dodamduck.ui.component.DodamDuckTextH2
import org.chosun.dodamduck.ui.component.DodamDuckTextH3
import org.chosun.dodamduck.ui.component.FocusTextField
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.LightBrown80
import org.chosun.dodamduck.utils.Utils.uriToMultipartBody

@Composable
fun PostWriteScreen(
    navController: NavController,
    postViewModel: PostViewModel = hiltViewModel()
) {
    var imageList by remember { mutableStateOf<List<Uri>>(listOf()) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageList = ((imageList + uri) ?: throw NullPointerException()) as List<Uri>
    }

    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var detailDescription by remember { mutableStateOf("") }
    var transactionType by remember { mutableIntStateOf(1) }
    val uploadSuccess by postViewModel.uploadSuccess.collectAsState(initial = false)

    val onImageClick = { galleryLauncher.launch("image/*") }

    val handleTradeUpload = {
        val userIdBody =
            DodamDuckData.userInfo.userID.toRequestBody("text/plain".toMediaTypeOrNull())
        val categoryIdBody =
            "3".toRequestBody("text/plain".toMediaTypeOrNull())
        val titleBody = title.toRequestBody("text/plain".toMediaTypeOrNull())
        val contentBody = detailDescription.toRequestBody("text/plain".toMediaTypeOrNull())
        val locationBody =
            DodamDuckData.userInfo.location.toRequestBody("text/plain".toMediaTypeOrNull())
        val filePart = imageList[0].uriToMultipartBody(context)

        postViewModel.uploadPost(
            userIdBody,
            categoryIdBody,
            titleBody,
            contentBody,
            locationBody,
            filePart
        )
    }

    LaunchedEffect(key1 = uploadSuccess) {
        if (uploadSuccess)
            navController.popBackStack()
    }

    PostWriteContent(
        navController = navController,
        onSubmit = handleTradeUpload,
        onTitleTextChange = { title = it },
        onDescriptionChange = { detailDescription = it },
        title = title,
        detailDescription = detailDescription,
        onImageClick = onImageClick
    )
}

@Composable
fun PostWriteContent(
    navController: NavController,
    onSubmit: () -> Unit,
    onTitleTextChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onImageClick: () -> Unit,
    title: String,
    detailDescription: String,
    imageList: List<Uri> = listOf(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            PostWriteScreenHeader(navController, onSubmit)
            PostSubjectSelect(modifier = Modifier.padding(horizontal = 12.dp))
            PostWriteMessageCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            )
            PostWriteInputField(
                modifier = Modifier.height(48.dp),
                text = title,
                onValueChange = onTitleTextChange,
                label = "제목을 입력하세요",
            )
            PostWriteInputField(
                modifier = Modifier.weight(1f),
                text = detailDescription,
                onValueChange = onDescriptionChange,
                label = stringResource(id = R.string.post_information_message),
            )
            Divider()
            PostWriteBottom(onImageClick = onImageClick)
        }
    }
}

@Composable
fun PostWriteScreenHeader(
    navController: NavController,
    onSubmit: () -> Unit
) {
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
                contentDescription = "Close Icon"
            )
        }
        Spacer(Modifier.run { weight(0.5f) })
        DodamDuckTextH2(
            Modifier.weight(2f),
            text = stringResource(R.string.post_write),
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        DodamDuckTextH3(
            modifier = Modifier.weight(1f).clickable(onClick = onSubmit),
            text = "완료",
            color = Color.Gray,
            textAlign = TextAlign.End
        )
    }
    Divider()
}

@Composable
fun PostSubjectSelect(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = CenterVertically
    ) {
        DodamDuckTextH2(
            modifier = Modifier.weight(1f),
            text = "게시글의 주제 선택해주세요."
        )
        IconButton(onClick = {}, modifier = Modifier.wrapContentSize()) {
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .weight(1f),
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Close Icon",
            )
        }
    }
    Divider()
}

@Composable
fun PostWriteMessageCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        color = LightBrown80
    ) {
        DodamDuckText(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
            text = "안내 중고거래 관련, 명예훼손, 광고/홍보 목적의 글은\n" +
                    "작성하실 수 없습니다.",
            color = Color.White,
            fontWeight = FontWeight.Light,
            fontSize = 12
        )
    }
}

@Composable
fun PostWriteInputField(
    modifier: Modifier = Modifier,
    text: String,
    label: String = "",
    onValueChange: (String) -> Unit
) {
    FocusTextField(
        modifier = modifier,
        value = text,
        onValueChange = onValueChange,
        label = label
    )
}

@Composable
fun PostWriteBottom(
    onImageClick: () -> Unit
) {
    Row {
        IconButton(
            onClick = onImageClick,
            modifier = Modifier
                .wrapContentSize()
        ) {
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .weight(1f),
                painter = painterResource(id = R.drawable.ic_img_26),
                contentDescription = "Close Icon",
            )
        }
        DodamDuckText(
            modifier = Modifier.align(alignment = CenterVertically),
            text = stringResource(R.string.photo), fontSize = 15
        )
    }
}

@Preview
@Composable
fun PostWritePreview() {
    DodamDuckTheme {
        PostWriteContent(
            rememberNavController(),
            onSubmit = {},
            {},
            {},
            {},
            "",
            ""
        )
    }
}