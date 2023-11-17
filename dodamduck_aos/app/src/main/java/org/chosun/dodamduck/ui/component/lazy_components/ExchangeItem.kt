package org.chosun.dodamduck.ui.component.lazy_components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.chosun.dodamduck.R
import org.chosun.dodamduck.model.dto.Trade
import org.chosun.dodamduck.ui.component.CommentIcon
import org.chosun.dodamduck.ui.component.DodamDuckTextH3
import org.chosun.dodamduck.utils.DateUtil.formatDateDiff

@Composable
@Preview(showBackground = true)
fun PreviewExchangeItemList() {
    Box(modifier = Modifier.fillMaxWidth()) {
        ExchangeItemList()
    }
}

@Composable
fun ExchangeItemList(
    modifier: Modifier = Modifier,
    items: List<Trade> = listOf()
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 10.dp)
    ) {
        items(items.size) {
            ExchangeItemItem(items[it])
            Divider(modifier = Modifier.padding(top = 6.dp, bottom = 16.dp))
        }
    }
}

@Composable
fun ExchangeItemItem(item: Trade) {
    Row {
        AsyncImage(
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
            model = item.image_url ,
            contentDescription = "Image"
        )

        Column(
            modifier = Modifier.padding(start = 6.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            DodamDuckTextH3(text = item.title)
            Text(
                text = item.location + " \u00B7 " + item.created_at.formatDateDiff() + " \u00B7 조회 " + item.views,
                fontSize = 9.sp,
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
