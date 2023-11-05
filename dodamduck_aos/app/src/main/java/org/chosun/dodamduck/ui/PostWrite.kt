package org.chosun.dodamduck.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.component.DodamDuckText
import org.chosun.dodamduck.ui.component.DodamDuckTextH2
import org.chosun.dodamduck.ui.component.DodamDuckTextH3
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.LightBrown80

@Composable
fun PostWriteScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            PostWriteScreenHeader(navController)
            PostSubjectSelect(modifier = Modifier.padding(horizontal = 12.dp))
            PostWriteMessageCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            )
            PostWriteInputField(
                modifier = Modifier.fillMaxWidth(),
                text = "제목을 입력하세요",
                fontSize = 15
            )
            PostWriteInputField(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.post_information_message),
                fontSize = 12
            )
            Divider()
            PostWriteBottom()
        }
    }
}

@Composable
fun PostWriteScreenHeader(navController: NavController) {
    Row(
        modifier = Modifier.padding(end = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {navController.popBackStack()}, modifier = Modifier.wrapContentSize()) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Close Icon"
            )
        }
        Spacer(Modifier.run { weight(0.5f) })
        DodamDuckTextH2(
            Modifier.weight(2f),
            text = stringResource(R.string.post_write),
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        DodamDuckTextH3(
            modifier = Modifier.weight(1f),
            text = "완료",
            color = Color.Gray,
            textAlign = TextAlign.End
        )
    }
    Divider()
}

@Composable
fun PostSubjectSelect(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DodamDuckTextH2(
            modifier = Modifier.weight(1f),
            text = "게시글의 주제 선택해주세요."
        )
        IconButton(onClick = {}, modifier = Modifier.wrapContentSize()) {
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .weight(1f),
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Close Icon",
            )
        }
    }
    Divider()
}

@Composable
fun PostWriteMessageCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        color = LightBrown80
    ) {
        DodamDuckText(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
            text = "안내 중고거래 관련, 명예훼손, 광고/홍보 목적의 글은\n" +
                    "작성하실 수 없습니다.",
            color = Color.White,
            fontWeight = FontWeight.Light,
            fontSize = 12
        )
    }
}

@Composable
fun PostWriteInputField(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int
) {
    var rememberText by remember { mutableStateOf("") }
    TextField(
        modifier = modifier,
        value = rememberText,
        onValueChange = { rememberText = it },
        label = {
            Text(text = text, fontSize = fontSize.sp)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun PostWriteBottom() {
    Row {
        IconButton(onClick = {}, modifier = Modifier.wrapContentSize()) {
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .weight(1f),
                painter = painterResource(id = R.drawable.ic_img_26),
                contentDescription = "Close Icon",
            )
        }
        DodamDuckText(
            modifier = Modifier.align(alignment = CenterVertically),
            text = stringResource(R.string.photo), fontSize = 15
        )
    }
}

@Preview
@Composable
fun PostWritePreview() {
    DodamDuckTheme {
        PostWriteScreen(rememberNavController())
    }
}