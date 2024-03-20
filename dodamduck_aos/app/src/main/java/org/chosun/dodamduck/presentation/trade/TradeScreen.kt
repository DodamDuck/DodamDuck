package org.chosun.dodamduck.presentation.trade

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.component.DodamDuckTextH2
import org.chosun.dodamduck.ui.component.lazy_components.ExchangeItemList
import org.chosun.dodamduck.ui.navigation.BottomNavItem
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.DodamDuckTheme

@Composable
fun TradeScreen(
    navController: NavHostController,
    tradeViewModel: TradeViewModel = hiltViewModel()
) {
    val state by tradeViewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    var lastBackPress by remember { mutableLongStateOf(0L) }

    BackHandler {
        val currentTimestamp = System.currentTimeMillis()

        if (currentTimestamp - lastBackPress < 2000) {
            (context as? Activity)?.finish()
        } else {
            tradeViewModel.sendSideEffect(TradeSideEffect.Toast("뒤로 버튼을 한 번 더 누르면 종료됩니다."))
            lastBackPress = currentTimestamp
        }
    }

    LaunchedEffect(Unit) {
        tradeViewModel.getTradeLists()

        tradeViewModel.effect.collectLatest {  effect ->
            when(effect) {
                is TradeSideEffect.Toast
                -> Toast.makeText(context, effect.text, Toast.LENGTH_SHORT).show()

                is TradeSideEffect.NavigatePopBackStack
                -> navController.popBackStack()

                is TradeSideEffect.NavigateToTradeWrite
                -> navController.navigate(BottomNavItem.TradeWrite.screenRoute)

                is TradeSideEffect.NavigateToSearch
                -> navController.navigate(BottomNavItem.Search.screenRoute)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            TradeHeader(
                onSearchIconClick = { tradeViewModel.sendSideEffect(TradeSideEffect.NavigateToSearch) }
            )
            ExchangeItemList(
                modifier = Modifier.padding(top = 24.dp),
                items = state.tradeList,
                navController = navController
            )
        }

        OutlinedButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .height(60.dp)
                .padding(end = 8.dp, bottom = 8.dp),
            onClick = { tradeViewModel.sendSideEffect(TradeSideEffect.NavigateToTradeWrite) },
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
fun TradeHeader(
    onSearchIconClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 12.dp)
    ) {
        DodamDuckTextH2(text = "빛가람동", color = Brown)
        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Arrow Icon")
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.clickable { onSearchIconClick() },
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon"
        )
    }
}

@Preview
@Composable
fun TradePreview() {
    DodamDuckTheme {
        TradeScreen(rememberNavController())
    }
}