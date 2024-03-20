package org.chosun.dodamduck.presentation.search

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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.chosun.dodamduck.R
import org.chosun.dodamduck.data.database.SearchHistory
import org.chosun.dodamduck.data.dto.CategoryDTO
import org.chosun.dodamduck.data.dto.SearchDTO
import org.chosun.dodamduck.data.dto.Trade
import org.chosun.dodamduck.data.dto.convertCategoryList
import org.chosun.dodamduck.presentation.trade.TradeViewModel
import org.chosun.dodamduck.ui.component.FocusTextField
import org.chosun.dodamduck.ui.component.lazy_components.ExchangeItemList
import org.chosun.dodamduck.ui.component.lazy_components.TagLazyRow
import org.chosun.dodamduck.ui.modifier.addFocusCleaner
import org.chosun.dodamduck.ui.navigation.BottomNavItem
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.Gray5

@Composable
fun SearchScreen(
    navController: NavController,
    tradeViewModel: TradeViewModel = hiltViewModel()
) {
    var searchText by remember { mutableStateOf("") }
    val state by tradeViewModel.uiState.collectAsStateWithLifecycle()
    val searchList by tradeViewModel.searchQueryList.collectAsState(initial = null)
    val popularSearchList by tradeViewModel.popularSearchList.collectAsState(initial = null)

    LaunchedEffect(key1 = Unit) {
        tradeViewModel.fetchSearchQuery()
        tradeViewModel.fetchPopularSearches()
    }

    SearchContent(
        navController = navController,
        searchText = searchText,
        searchEvent = {
            tradeViewModel.searchTrade(searchText.trim())
            tradeViewModel.insertSearchQuery(searchText.trim())
        },
        onSearchTextChange = { searchText = it },
        state.tradeList,
        searchList ?: listOf(),
        onSearchDelete = { query ->
            tradeViewModel.deleteSearchQuery(query)
        },
        popularSearchList = popularSearchList ?: listOf(),
        onSearchDeleteAll = { tradeViewModel.deleteAllSearchQuery() },
        onTagSelected = {
            searchText = it
            tradeViewModel.searchTrade(searchText.trim())
        },
        onItemClick = { id ->
            navController.navigate("${BottomNavItem.PostDetail.screenRoute}/${id}/trade") {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
            }
        }
    )
}

@Composable
fun SearchContent(
    navController: NavController,
    searchText: String,
    searchEvent: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    tradeList: List<Trade>,
    searchList: List<SearchHistory>,
    onSearchDelete: (String) -> Unit,
    popularSearchList: List<SearchDTO>,
    onSearchDeleteAll: () -> Unit,
    onTagSelected: (String) -> Unit,
    onItemClick: (String) -> Unit
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
            SearchTagContent(popularSearchList, onTagSelected)
            RecentSearchList(
                searchList,
                onSearchDelete = onSearchDelete,
                onSearchDeleteAll = onSearchDeleteAll
            )
        } else {
            ExchangeItemList(
                modifier = Modifier.padding(top = 24.dp),
                items = tradeList,
                onItemClick = { id -> onItemClick(id) }
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
fun SearchTagContent(
    popularSearchList: List<SearchDTO>,
    onTagSelected: (String) -> Unit
) {
    val categories = popularSearchList.convertCategoryList()
    var selectedTag by remember { mutableStateOf(CategoryDTO("0", "")) }

    Column(
        modifier = Modifier.padding(top = 20.dp, start = 12.dp)
    ) {
        Text("인기 검색", fontWeight = FontWeight.SemiBold)
        TagLazyRow(
            modifier = Modifier.padding(top = 12.dp),
            categories = categories,
            selectedTag = selectedTag,
            onTagSelected = {
                selectedTag = it
                onTagSelected(selectedTag.name)
            }
        )
    }
}

@Composable
fun RecentSearchList(
    searchList: List<SearchHistory>,
    onSearchDelete: (String) -> Unit,
    onSearchDeleteAll: () -> Unit
) {
    Column(
        modifier = Modifier.padding(top = 20.dp, start = 12.dp, end = 12.dp)
    ) {
        Row {
            Text("최근 검색", fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.weight(1f))
            Text(
                modifier = Modifier.clickable(onClick = onSearchDeleteAll),
                text = "전체 삭제",
                color = Color.Gray
            )
        }

        LazyColumn(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            items(searchList.size) { index ->
                RecentSearchListItem(
                    searchList[index].query,
                    onSearchDelete = onSearchDelete
                )
            }
        }
    }
}

@Composable
fun RecentSearchListItem(
    searchText: String,
    onSearchDelete: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
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
            text = searchText
        )
        Icon(
            modifier = Modifier.clickable { onSearchDelete(searchText) },
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
            listOf(),
            listOf(
                SearchHistory(query = "붕어"),
                SearchHistory(query = "붕어"),
                SearchHistory(query = "붕어")
            ),
            onSearchDelete = {},
            popularSearchList = listOf(
                SearchDTO("1", "뽀로로", "", ""),
                SearchDTO("2", "블록놀이", "", "")
            ),
            onSearchDeleteAll = {},
            onTagSelected = {},
            onItemClick = {}
        )
    }
}