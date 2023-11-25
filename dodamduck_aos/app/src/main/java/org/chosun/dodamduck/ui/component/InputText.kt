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
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
    borderWidth: Int = 2,
    passwordVisible: Boolean = true,
    onVisibilityChanged: () -> Unit = {},
    iconState: Boolean = false,
    onValueChange: (String) -> Unit = {},
    text: String = ""
    ) {
    DodamDuckInputSurface(
        modifier = modifier
            .width(width = width.dp)
            .height(height = height.dp),
        borderColor = borderColor,
        borderWidth = borderWidth
    ) {
        val visualTransformation =
            if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = onValueChange,
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
                ),
                visualTransformation = visualTransformation,
                trailingIcon = {
                    if (iconVisible) {
                        IconButton(onClick = onVisibilityChanged) {
                            VisibleIcon(
                                modifier
                                    .wrapContentSize()
                                    .padding(end = 12.dp),
                                isVisible = iconState
                            )
                        }
                    }
                }
            )
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
        DodamDuckTextField(label = stringResource(id = R.string.email), iconVisible = true)
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
fun AuthInputTextList(
    labelList: List<String>,
    onUserIDChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onConfirmChange: (String) -> Unit = {},
    emailText: String = "",
    passwordText: String = "",
    confirmPasswordText: String = ""
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordConfirmVisible by remember { mutableStateOf(false) }

    LazyColumn(modifier = Modifier.padding(vertical = 5.dp)) {
        items(labelList.size) { index ->
            if (index == 1 || index == 2) {
                PasswordField(
                    label = labelList[index],
                    isPasswordVisible = if (index == 1) passwordVisible else passwordConfirmVisible,
                    onVisibilityChanged = {
                        if (index == 1) passwordVisible = !passwordVisible
                        else passwordConfirmVisible = !passwordConfirmVisible
                    },
                    iconState = if (index == 1) passwordVisible else passwordConfirmVisible,
                    onValueChange = if(index == 1) onPasswordChange else onConfirmChange,
                    text = if (index == 1) passwordText else confirmPasswordText,
                )
            } else {
                DodamDuckTextField(
                    label = labelList[index],
                    width = 296,
                    height = 55,
                    iconVisible = false,
                    modifier = Modifier.padding(vertical = 10.dp),
                    onValueChange = onUserIDChange,
                    text = emailText
                )
            }
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
    modifier: Modifier = Modifier,
    onSendButtonClick: () -> Unit = {},
    onTextFieldChange: (String) -> Unit = {},
    value: String = ""
) {
    Row(
        modifier = modifier
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
                Text(text = "메세지를 입력하세요", fontSize = 14.sp)
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

@Composable
fun PasswordField(
    label: String,
    isPasswordVisible: Boolean,
    onVisibilityChanged: () -> Unit,
    iconState: Boolean,
    onValueChange: (String) -> Unit,
    text: String
) {
    DodamDuckTextField(
        label = label,
        width = 296,
        height = 55,
        iconVisible = true,
        passwordVisible = isPasswordVisible,
        onVisibilityChanged = onVisibilityChanged,
        modifier = Modifier.padding(vertical = 10.dp),
        iconState = iconState,
        onValueChange = onValueChange,
        text = text
    )
}
