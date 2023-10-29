package org.chosun.dodamduck.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.chosun.dodamduck.R

@Composable
@Preview(showBackground = true)
fun PreviewToyList() {
    Box(modifier = Modifier.fillMaxWidth()) {
        ToyList()
    }
}

@Composable
fun ToyList() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),  // 한 행에 2개의 아이템
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
    ) {
        items(8) {  // 예제로 4개의 아이템을 추가
            ToyItem()
        }
    }
}

@Composable
fun ToyItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = stringResource(R.string.dummy_toy_item_metropolitan_city))
            Text(text = stringResource(R.string.dummy_toy_item_district_office))
            Image(
                painter = painterResource(id = R.drawable.ic_toy_48),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.dummy_toy_item_name), fontSize = 12.sp)
                Text(text = stringResource(R.string.dummy_toy_item_classification), fontSize = 10.sp)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.dummy_toy_item_age_limit), fontSize = 10.sp)
                Text(text = stringResource(R.string.dummy_toy_item_price), fontSize = 10.sp)
            }
            OutlinedButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                border = BorderStroke(1.dp, Color.Gray),
                onClick = { /* TODO: 버튼 클릭 이벤트 처리 */ }
            ) {
                Text(text = stringResource(R.string.ask_rental), color = Color.Gray)
            }
        }
    }
}
