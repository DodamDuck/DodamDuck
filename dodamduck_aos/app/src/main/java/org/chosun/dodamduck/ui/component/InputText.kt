package org.chosun.dodamduck.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.theme.Primary

private const val DEFAULT_WIDTH = 200
private const val DEFAULT_HEIGHT = 50

@Composable
fun AuthTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    iconVisible: Boolean = true,
    width: Int = DEFAULT_WIDTH,
    height: Int = DEFAULT_HEIGHT
) {
    AuthInputSurface(
        modifier = modifier
            .width(width = width.dp)
            .height(height = height.dp)
    ) {
        var text by remember { mutableStateOf("") }

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                Modifier
                    .wrapContentWidth()
                    .weight(1f)
                    .fillMaxHeight(),
                label = {
                    Text(text = label)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            if (iconVisible) {
                VisibleIcon(
                    modifier
                        .wrapContentSize()
                        .padding(end = 12.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewLoginTextField() {
    Box(
        Modifier
            .wrapContentSize()
            .background(Primary)
    ) {
        AuthTextField(label = stringResource(id = R.string.email), iconVisible = false)
    }
}

@Composable
fun AuthInputTextList(labelList: List<String>) {
    LazyColumn(modifier = Modifier.padding(vertical = 5.dp)) {
        items(labelList.size) { index ->
            AuthTextField(
                label = labelList[index],
                width = 296,
                height = 55,
                iconVisible = index != 0,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }
}

@Composable
@Preview
fun PreviewAuthInputTextList() {
    Box(
        Modifier
            .wrapContentSize()
            .background(Primary)
    ) {
        AuthInputTextList(
            labelList = listOf(
                stringResource(R.string.email),
                stringResource(R.string.create_password),
                stringResource(R.string.confirm_password)
            )
        )
    }
}