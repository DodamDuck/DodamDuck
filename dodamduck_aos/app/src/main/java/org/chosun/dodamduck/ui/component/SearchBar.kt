package org.chosun.dodamduck.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.chosun.dodamduck.ui.theme.Secondary
import org.chosun.dodamduck.ui.theme.White9

@Composable
fun DodamDuckSearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onSearchTextChange: (String) -> Unit
) {
    Box(
        modifier = modifier
            .padding(horizontal = 50.dp)
            .background(
                color = White9,
                shape = RoundedCornerShape(32.dp)
            )
            .height(50.dp)
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(32.dp))
            .clickable {
                /** todo Click Event
                 **/
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .background(color = White9,
                        shape = RoundedCornerShape(32.dp)
                ).fillMaxSize()
        ) {
            FocusTextField(
                modifier = Modifier.weight(1f).padding(start = 12.dp),
                value = value,
                onValueChange = onSearchTextChange
            )

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp).offset(x = (-20).dp, y = (2).dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    Surface(color = Secondary) {
        DodamDuckSearchBar(
            value = "",
            onSearchTextChange = {}
        )
    }
}
