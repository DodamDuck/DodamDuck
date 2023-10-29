package org.chosun.dodamduck.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.chosun.dodamduck.R

@Composable
@Preview(showBackground = true)
fun PreviewExchangeItemList() {
    Box(modifier = Modifier.fillMaxWidth()) {
        ExchangeItemList()
    }
}

@Composable
fun ExchangeItemList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 10.dp)
    ) {
        items(8) {
            ExchangeItemItem()
            Divider(modifier = Modifier.padding(top = 6.dp, bottom = 16.dp))
        }
    }
}

@Composable
fun ExchangeItemItem() {
    Row{
        Image(
            modifier = Modifier.border(1.dp, Color.Gray, RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.ic_duck),
            contentDescription = "Exchange Item"
        )

        Column(
            modifier = Modifier.padding(start = 6.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            DodamDuckTextH3(text = stringResource(id = R.string.dummy_toy_item_name))
            Text(
                text = stringResource(R.string.dummy_post_info_item),
                fontSize = 9.sp,
                color = Color.Gray
            )
            DodamDuckTextH3(
                text = "교환",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Image(painter = painterResource(id = R.drawable.ic_comment_26),
            contentDescription = "Chat Icon",
            Modifier
                .size(32.dp)
                .align(alignment = Alignment.Bottom)
        )
        Text(
            modifier = Modifier.align(alignment = Alignment.Bottom),
            fontSize = 14.sp,
            text = "3",
            color = Color.Gray
        )
    }
}
