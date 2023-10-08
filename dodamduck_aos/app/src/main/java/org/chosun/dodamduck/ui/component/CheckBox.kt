package org.chosun.dodamduck.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.CheckboxDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import org.chosun.dodamduck.R

@Composable
fun DodamDuckCheckBox(
    text: String,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
) {
    var checkedState by remember { mutableStateOf(true) }

    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            Checkbox(
                checked = checkedState,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Transparent,
                    uncheckedColor = Color.Transparent,
                    checkmarkColor = Color.Black,
                    disabledColor = Color.Gray,
                    disabledIndeterminateColor = Color.Gray
                )
            )
        }
        Text(text = text, Modifier.padding(start = 5.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBoxSamplePreview() {
    Box (modifier = Modifier.fillMaxSize()){
        DodamDuckCheckBox(
            stringResource(id = R.string.confirmed_the_terms_and_conditions),
            {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
