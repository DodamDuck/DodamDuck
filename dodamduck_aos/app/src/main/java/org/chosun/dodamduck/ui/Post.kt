package org.chosun.dodamduck.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.chosun.dodamduck.ui.component.DodamDuckTextH2
import org.chosun.dodamduck.ui.component.lazy_components.PostItem
import org.chosun.dodamduck.ui.component.lazy_components.TagLazyRow
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.DodamDuckTheme

@Composable
fun PostScreen() {
    val tags = listOf("주제", "인기글", "지식공유", "지식유형", "인기글", "인기글", "인기글")
    var selectedTag by remember { mutableStateOf(tags.first()) }

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

            LazyColumn() {
                items(10) {
                    PostItem(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp))
                    Divider()
                }
            }
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
        PostScreen()
    }
}