package org.chosun.dodamduck.presentation.post.write

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.chosun.dodamduck.R
import org.chosun.dodamduck.data.model.DodamDuckData
import org.chosun.dodamduck.data.dto.post.CategoryDto
import org.chosun.dodamduck.presentation.post.PostSideEffect
import org.chosun.dodamduck.presentation.post.PostViewModel
import org.chosun.dodamduck.ui.component.BottomSheet
import org.chosun.dodamduck.ui.component.BottomSheetText
import org.chosun.dodamduck.ui.component.DodamDuckText
import org.chosun.dodamduck.ui.component.DodamDuckTextH2
import org.chosun.dodamduck.ui.component.DodamDuckTextH3
import org.chosun.dodamduck.ui.component.FocusTextField
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.LightBrown80
import org.chosun.dodamduck.ui.util.getDrawableUri
import org.chosun.dodamduck.utils.Utils.uriToBitmap
import org.chosun.dodamduck.utils.Utils.uriToMultipartBody

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostWriteScreen(
    navController: NavController,
    postViewModel: PostViewModel = hiltViewModel()
) {
    val state by postViewModel.uiState.collectAsStateWithLifecycle()
    val effect by postViewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    val context = LocalContext.current
    var imageList by remember { mutableStateOf<List<Uri>>(listOf()) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageList = (imageList + uri) as List<Uri>
    }

    LaunchedEffect(key1 = effect) {
        when(effect) {
            is PostSideEffect.Toast
            -> Toast.makeText(context, (effect as PostSideEffect.Toast).text, Toast.LENGTH_SHORT).show()

            is PostSideEffect.NavigatePopBackStack
            -> navController.popBackStack()

            else -> {}
        }
    }

    LaunchedEffect(Unit) {
        postViewModel.getCategories()
    }

    var categoryType by remember { mutableStateOf(CategoryDto("", "게시글의 주제 선택해주세요.")) }
    var selectedCategoryType by remember { mutableStateOf("게시글의 주제 선택해주세요.") }

    LaunchedEffect(key1 = categoryType) {
        selectedCategoryType = categoryType.name
    }

    var title by remember { mutableStateOf("") }
    var detailDescription by remember { mutableStateOf("") }

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    PostWriteContent(
        navController = navController,
        context = context,
        onSubmit = {
            postViewModel.uploadPost(
                DodamDuckData.userInfo.userID,
                categoryType.id,
                title,
                detailDescription,
                DodamDuckData.userInfo.location,
                if (imageList.isEmpty()) context.getDrawableUri(R.drawable.img_no)
                    .uriToMultipartBody(context) else imageList[0].uriToMultipartBody(context)
            )
        },
        onTitleTextChange = { title = it },
        onDescriptionChange = { detailDescription = it },
        title = title,
        detailDescription = detailDescription,
        onImageClick = { galleryLauncher.launch("image/*") },
        onCategoryListClick = { showBottomSheet = true },
        selectedCategory = selectedCategoryType,
        imageList = imageList
    )

    if (showBottomSheet) {
        BottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            PostWriteBottomSheetContent(
                categoryList = state.categories,
                onCategoryClick = {
                    categoryType = it
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun PostWriteContent(
    navController: NavController,
    context: Context = LocalContext.current,
    onSubmit: () -> Unit,
    onTitleTextChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onImageClick: () -> Unit,
    onCategoryListClick: () -> Unit,
    title: String,
    detailDescription: String,
    selectedCategory: String = "",
    imageList: List<Uri> = listOf(),
) {
    val imageModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp, vertical = 8.dp)
        .clip(RoundedCornerShape(25.dp))
        .aspectRatio(5f / 5f)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            PostWriteScreenHeader(navController, onSubmit)
            PostCategorySelect(
                modifier = Modifier.padding(horizontal = 12.dp),
                onCategoryListClick = onCategoryListClick,
                selectedCategory = selectedCategory
            )
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

            if(imageList.isNotEmpty()) {
                Image(
                    contentScale = ContentScale.Crop,
                    modifier = imageModifier,
                    bitmap = imageList[0].uriToBitmap(context)!!.asImageBitmap(),
                    contentDescription = "Post Image"
                )
            }

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
        verticalAlignment = CenterVertically
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
            modifier = Modifier
                .weight(1f)
                .clickable(onClick = onSubmit),
            text = "완료",
            color = Color.Gray,
            textAlign = TextAlign.End
        )
    }
    Divider()
}

@Composable
fun PostCategorySelect(
    modifier: Modifier = Modifier,
    onCategoryListClick: () -> Unit,
    selectedCategory: String = "",
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onCategoryListClick),
        verticalAlignment = CenterVertically
    ) {
        DodamDuckTextH2(
            modifier = Modifier.weight(1f),
            text = selectedCategory
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
            text = stringResource(R.string.post_write_info_message_card),
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

@Composable
fun PostWriteBottomSheetContent(
    categoryList: List<CategoryDto>?,
    onCategoryClick: (CategoryDto) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        categoryList?.size?.let { size ->
            repeat(size) { index ->
                BottomSheetText(
                    modifier = Modifier.clickable { onCategoryClick(categoryList[index]) },
                    text = categoryList[index].name
                )
                if (index != size - 1)
                    Divider(modifier = Modifier.padding(vertical = 12.dp))
            }
        }
    }
}

@Preview
@Composable
fun PostWritePreview() {
    DodamDuckTheme {
        PostWriteContent(
            rememberNavController(),
            onSubmit = {},
            onTitleTextChange = {},
            onDescriptionChange = {},
            onImageClick = {},
            onCategoryListClick = {},
            title = "",
            detailDescription = "",
        )
    }
}