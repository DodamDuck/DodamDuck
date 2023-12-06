package org.chosun.dodamduck.ui

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.chosun.dodamduck.R
import org.chosun.dodamduck.model.dto.CategoryDTO
import org.chosun.dodamduck.model.dto.Trade
import org.chosun.dodamduck.model.viewmodel.TradeViewModel
import org.chosun.dodamduck.ui.component.FocusTextField
import org.chosun.dodamduck.ui.component.lazy_components.ExchangeItemList
import org.chosun.dodamduck.ui.component.lazy_components.TagLazyRow
import org.chosun.dodamduck.ui.modifier.addFocusCleaner
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.Gray5

@Composable
fun SearchScreen(
    navController: NavController,
    tradeViewModel: TradeViewModel = hiltViewModel()
) {
    var searchText by remember { mutableStateOf("") }
    val postLists by tradeViewModel.postLists.collectAsState(initial = null)

    SearchContent(
        navController = navController,
        searchText = searchText,
        searchEvent = { tradeViewModel.searchPost(searchText.trim()) },
        onSearchTextChange = { searchText = it },
        postLists ?: listOf()
    )
}

@Composable
fun SearchContent(
    navController: NavController,
    searchText: String,
    searchEvent: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    tradeList: List<Trade>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .addFocusCleaner(LocalFocusManager.current)
            .onKeyEvent { keyEvent ->
                if (keyEvent.key == Key.Enter && keyEvent.type == KeyEventType.KeyUp) {
                    searchEvent()
                    true
                } else false
            }
    ) {
        SearchScreenHeader(
            navController,
            searchText = searchText,
            onSearchTextChange = onSearchTextChange
        )
        if (tradeList.isEmpty()) {
            SearchTagContent()
            RecentSearchList()
        } else {
            ExchangeItemList(
                modifier = Modifier.padding(top = 24.dp),
                items = tradeList,
                navController = navController
            )
        }
    }
}

@Composable
fun SearchScreenHeader(
    navController: NavController,
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .padding(top = 10.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(48.dp)
                .clickable { navController.popBackStack() },
            imageVector = Icons.Sharp.KeyboardArrowLeft,
            contentDescription = "Left Arrow Icon"
        )

        Box(
            modifier = Modifier
                .padding(end = 30.dp)
                .fillMaxSize()
                .background(color = Gray5, shape = RoundedCornerShape(6.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (searchText == "")
                Text(text = "검색어를 입력하세요", color = Color.Gray)

            FocusTextField(
                modifier = Modifier.fillMaxSize(),
                value = searchText,
                onValueChange = onSearchTextChange
            )
        }

    }
}

@Composable
fun SearchTagContent() {
    val categories = listOf(CategoryDTO("1", "뽀로로"), CategoryDTO("2", "블록놀이"))
    var selectedTag by remember { mutableStateOf(CategoryDTO("1", "뽀로로")) }

    Column(
        modifier = Modifier.padding(top = 20.dp, start = 12.dp)
    ) {
        Text("인기 검색", fontWeight = FontWeight.SemiBold)
        TagLazyRow(
            modifier = Modifier.padding(top = 12.dp),
            categories = categories,
            selectedTag = selectedTag,
            onTagSelected = { selectedTag = it }
        )
    }
}

@Composable
fun RecentSearchList() {
    Column(
        modifier = Modifier.padding(top = 20.dp, start = 12.dp, end = 12.dp)
    ) {
        Row {
            Text("최근 검색", fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.weight(1f))
            Text("전체 삭제", color = Color.Gray)
        }

        LazyColumn(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            item {
                RecentSearchListItem()
            }
        }
    }
}

@Composable
fun RecentSearchListItem() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_clock),
            tint = Color.Gray,
            contentDescription = "Clock Icon"
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = "주저리 주저리"
        )
        Icon(
            imageVector = Icons.Default.Close,
            tint = Color.Gray,
            contentDescription = "Close Icon"
        )
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    DodamDuckTheme {
        SearchContent(
            navController = rememberNavController(),
            searchText = "",
            searchEvent = {},
            onSearchTextChange = {},
            listOf()
        )
    }
}