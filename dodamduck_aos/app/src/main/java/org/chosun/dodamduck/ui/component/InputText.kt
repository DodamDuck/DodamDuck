package org.chosun.dodamduck.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.sp
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.theme.Primary

private const val DEFAULT_WIDTH = 200
private const val DEFAULT_HEIGHT = 50

@Composable
fun DodamDuckTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    iconVisible: Boolean = true,
    width: Int = DEFAULT_WIDTH,
    height: Int = DEFAULT_HEIGHT,
    borderColor: Color = Color.Black,
    borderWidth: Int = 2
) {
    DodamDuckInputSurface(
        modifier = modifier
            .width(width = width.dp)
            .height(height = height.dp),
        borderColor = borderColor,
        borderWidth = borderWidth
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
                singleLine = true,
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
fun OutlineTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    width: Int = DEFAULT_WIDTH,
    height: Int = DEFAULT_HEIGHT,
    borderColor: Color = Color.Black,
    borderWidth: Int = 2,
    onValueChange: (String) -> Unit = {},
    value: String = ""
) {
    DodamDuckInputSurface(
        modifier = modifier
            .width(width = width.dp)
            .height(height = height.dp),
        shape = RoundedCornerShape(10.dp),
        borderColor = borderColor,
        borderWidth = borderWidth
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                Modifier.fillMaxSize(),
                label = {
                    Text(text = label)
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
            )
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
        DodamDuckTextField(label = stringResource(id = R.string.email), iconVisible = false)
    }
}

@Composable
@Preview
fun PreviewOutlineTextField() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        OutlineTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            label = stringResource(id = R.string.title),
            borderColor = Color.Gray,
            borderWidth = 1
        )
    }
}

@Composable
fun AuthInputTextList(labelList: List<String>) {
    LazyColumn(modifier = Modifier.padding(vertical = 5.dp)) {
        items(labelList.size) { index ->
            DodamDuckTextField(
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

@Composable
fun DodamDuckMessageInputField(
    onSendButtonClick: () -> Unit = {},
    onTextFieldChange: (String) -> Unit = {},
    value: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(34.dp)
                .clickable { },
            imageVector = Icons.Default.Add,
            contentDescription = "Add Button",
        )

        TextField(
            modifier = Modifier.weight(1f),
            value = value,
            onValueChange = onTextFieldChange,
            singleLine = true,
            label = {
                androidx.compose.material3.Text(text = "메세지를 입력하세요", fontSize = 14.sp)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Icon(
            modifier = Modifier
                .size(34.dp)
                .clickable(onClick = onSendButtonClick),
            imageVector = Icons.Default.Send,
            contentDescription = "Send Button",
        )
    }
}