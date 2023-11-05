package org.chosun.dodamduck.ui.component.lazy_components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.chosun.dodamduck.ui.component.DodamDuckText
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.LightBrown

@Composable
fun TagLazyRow(
    modifier: Modifier = Modifier,
    tags: List<String>,
    selectedTag: String,
    onTagSelected: (String) -> Unit
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(tags) { tag ->
            TagLazyItem(
                tag = tag,
                isSelected = tag == selectedTag,
                onTagSelected = onTagSelected
            )
        }
    }
}

@Composable
fun TagLazyItem(tag: String, isSelected: Boolean, onTagSelected: (String) -> Unit) {
    val backgroundColor = if (isSelected) Brown else LightBrown
    val textColor = if (isSelected) Color.White else Brown
    val borderColor = if (isSelected) Brown else Color.LightGray
    val shape = RoundedCornerShape(16.dp)

    Surface(
        modifier = Modifier
            .clip(shape)
            .border(0.5.dp, borderColor, shape)
            .clickable { onTagSelected(tag) },
        color = backgroundColor,
        shape = shape
    ) {
        DodamDuckText(
            text = tag,
            color = textColor,
            fontSize = 12,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TagLazyItemPreview() {
    val tags = listOf("주제", "인기글", "지식공유", "지식유형", "인기글", "인기글", "인기글")
    var selectedTag by remember { mutableStateOf(tags.first()) }

    TagLazyRow(
        tags = tags,
        selectedTag = selectedTag,
        onTagSelected = { selectedTag = it }
    )
}