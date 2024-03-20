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
import org.chosun.dodamduck.data.dto.post.CategoryDto
import org.chosun.dodamduck.ui.component.DodamDuckText
import org.chosun.dodamduck.ui.theme.Brown
import org.chosun.dodamduck.ui.theme.LightBrown

@Composable
fun TagLazyRow(
    modifier: Modifier = Modifier,
    categories: List<CategoryDto>,
    selectedTag: CategoryDto,
    onTagSelected: (CategoryDto) -> Unit
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            TagLazyItem(
                tag = category,
                isSelected = category.name == selectedTag.name,
                onTagSelected = onTagSelected
            )
        }
    }
}

@Composable
fun TagLazyItem(tag: CategoryDto, isSelected: Boolean, onTagSelected: (CategoryDto) -> Unit) {
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
            text = tag.name,
            color = textColor,
            fontSize = 12,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TagLazyItemPreview() {
    val categories = listOf(CategoryDto("12", "주제"), CategoryDto("13", "인기글"), CategoryDto("14","지식공유"))
    var selectedTag by remember { mutableStateOf(categories.first()) }

    TagLazyRow(
        categories = categories,
        selectedTag = selectedTag,
        onTagSelected = { selectedTag = it }
    )
}