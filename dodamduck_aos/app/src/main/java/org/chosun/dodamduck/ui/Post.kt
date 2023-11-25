package org.chosun.dodamduck.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.chosun.dodamduck.R
import org.chosun.dodamduck.model.viewmodel.PostViewModel
import org.chosun.dodamduck.ui.component.DodamDuckTextH2
import org.chosun.dodamduck.ui.component.lazy_components.PostItem
import org.chosun.dodamduck.ui.component.lazy_components.TagLazyRow
import org.chosun.dodamduck.ui.navigation.BottomNavItem
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.DodamDuckTheme

@Composable
fun PostScreen(
    navController: NavHostController,
    postViewModel: PostViewModel = hiltViewModel()
) {
    val tags = listOf("주제", "인기글", "지식공유", "지식유형", "인기글", "인기글", "인기글")
    var selectedTag by remember { mutableStateOf(tags.first()) }

    val postLists by postViewModel.postLists.collectAsState(initial = null)

    LaunchedEffect(Unit) {
        postViewModel.getPostLists()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            PostHeader()
            Divider(modifier = Modifier.padding(top = 12.dp))

            TagLazyRow(
                Modifier.padding(start = 8.dp, top = 10.dp),
                tags = tags,
                selectedTag = selectedTag,
                onTagSelected = { selectedTag = it }
            )
            Divider(modifier = Modifier.padding(top = 10.dp))

            val posts = postLists ?: listOf()
            LazyColumn {
                items(posts.size) {index ->
                    PostItem(modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                        .clickable {
                            postViewModel.uploadViewCount(posts[index].shareID)
                            navController.navigate("${BottomNavItem.PostDetail.screenRoute}/${posts[index].shareID}/post")
                                   },
                        item = posts[index]
                    )
                    Divider()
                }
            }
        }

        OutlinedButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .height(60.dp)
                .padding(end = 8.dp, bottom = 8.dp),
            onClick = { navController.navigate(BottomNavItem.PostWrite.screenRoute) },
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Brown),
            border = BorderStroke(width = 1.dp, color = Brown)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon", tint = Brown)
            Spacer(Modifier.padding(horizontal = 3.dp))
            Text(stringResource(R.string.writing), color = Brown)
        }
    }
}

@Composable
fun PostHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 12.dp)
    ) {
        DodamDuckTextH2(text = "빛가람동", color = Brown)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon"
        )
    }
}

@Preview
@Composable
fun PostPreview() {
    DodamDuckTheme {
        PostScreen(rememberNavController())
    }
}