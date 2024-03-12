package org.chosun.dodamduck.presentation.library

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.chosun.dodamduck.R
import org.chosun.dodamduck.data.dto.ToyInfo
import org.chosun.dodamduck.ui.component.BottomRoundedBox
import org.chosun.dodamduck.ui.component.DodamDuckIcon
import org.chosun.dodamduck.ui.component.DodamDuckSearchBar
import org.chosun.dodamduck.ui.component.DodamDuckTextH2
import org.chosun.dodamduck.ui.component.DodamDuckTitleText
import org.chosun.dodamduck.ui.component.lazy_components.ToyList
import org.chosun.dodamduck.ui.modifier.addFocusCleaner
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.Primary

@Composable
fun LibraryScreen(toyLibraryViewModel: ToyLibraryViewModel = hiltViewModel()) {
    val state by toyLibraryViewModel.uiState.collectAsStateWithLifecycle()
    val effect by toyLibraryViewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        toyLibraryViewModel.getToyInfos()
    }

    LaunchedEffect(key1 = effect) {
        when(effect) {
            is ToyLibrarySideEffect.Toast
            -> Toast.makeText(context, (effect as ToyLibrarySideEffect.Toast).text, Toast.LENGTH_LONG).show()

            else -> {}
        }
    }

    when {
        state.isError -> {
            toyLibraryViewModel.sendSideEffect(ToyLibrarySideEffect.Toast(stringResource(R.string.api_error)))
        }

        state.isLoading -> {
            // todo
        }
    }

    LibraryContent(state.toyList)
}

@Composable
fun LibraryContent(
    toyInfos: List<ToyInfo>?
) {
    val focusManager = LocalFocusManager.current
    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .background(Primary)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            BottomRoundedBox(
                modifier = Modifier.height(200.dp),
                startRound = 40
            ) {
                LibraryHeader()
                LibrarySearchBar(
                    searchText = searchText,
                    onSearchTextChange = { searchText = it }
                )
            }
            ToyList(toyInfos ?: listOf(), searchText)
        }
    }
}

@Composable
fun LibraryHeader() {
    DodamDuckTitleText(modifier = Modifier.padding(start = 12.dp, top = 20.dp))
    DodamDuckIcon(modifier = Modifier.padding(start = 134.dp, top = 20.dp), size = 46)
}

@Composable
fun LibrarySearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 26.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        DodamDuckTextH2(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 7.dp),
            text = stringResource(R.string.ask_for_toy_search),
            color = Brown,
            fontWeight = FontWeight.ExtraBold
        )

        DodamDuckSearchBar(
            value = searchText,
            onSearchTextChange = onSearchTextChange
        )
    }
}


@Preview
@Composable
fun LibraryPreview() {
    DodamDuckTheme {
        LibraryContent(
            toyInfos = listOf(
                ToyInfo(
                    "", "",
                    "", "",
                    "", "", "", "",
                    "", ""
                )
            )
        )
    }
}

