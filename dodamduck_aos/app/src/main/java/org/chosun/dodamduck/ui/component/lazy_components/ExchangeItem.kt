package org.chosun.dodamduck.ui.component.lazy_components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import org.chosun.dodamduck.data.dto.Trade
import org.chosun.dodamduck.presentation.trade.TradeViewModel
import org.chosun.dodamduck.ui.component.CommentIcon
import org.chosun.dodamduck.ui.component.DodamDuckTextH3
import org.chosun.dodamduck.ui.navigation.BottomNavItem
import org.chosun.dodamduck.utils.Utils.formatDateDiff

@Composable
@Preview(showBackground = true)
fun PreviewExchangeItemList() {
    Box(modifier = Modifier.fillMaxWidth()) {
        ExchangeItemList(navController = rememberNavController())
    }
}

@Composable
fun ExchangeItemList(
    modifier: Modifier = Modifier,
    items: List<Trade> = listOf(),
    navController: NavController,
    tradeViewModel: TradeViewModel = hiltViewModel()
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 10.dp)
    ) {
        items(items.size) {
            ExchangeItem(items[it], navController, tradeViewModel)
            Divider(modifier = Modifier.padding(top = 6.dp, bottom = 16.dp))
        }
    }
}

@Composable
fun ExchangeItem(item: Trade, navController: NavController, tradeViewModel: TradeViewModel) {
    Row(
        modifier = Modifier.clickable {
            tradeViewModel.uploadViewCount(item.post_id)
            navController.navigate("${BottomNavItem.PostDetail.screenRoute}/${item.post_id}/trade") {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
            }
        }
    ) {
        AsyncImage(
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
            model = item.image_url,
            contentDescription = "Image"
        )

        Column(
            modifier = Modifier.padding(start = 6.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            DodamDuckTextH3(text = item.title)
            Text(
                text = item.location + " \u00B7 " + item.created_at.formatDateDiff() + " \u00B7 조회 " + item.views,
                fontSize = 12.sp,
                color = Color.Gray
            )
            DodamDuckTextH3(
                text = item.category_name,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        CommentIcon(
            modifier = Modifier
                .align(alignment = Alignment.Bottom),
            text = item.comment_count,
            size = 34.dp
        )
    }
}
