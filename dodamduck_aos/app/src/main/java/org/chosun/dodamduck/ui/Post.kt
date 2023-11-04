package org.chosun.dodamduck.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.component.DodamDuckRadioButton
import org.chosun.dodamduck.ui.component.DodamDuckText
import org.chosun.dodamduck.ui.component.DodamDuckTextH2
import org.chosun.dodamduck.ui.component.DodamDuckTextH3
import org.chosun.dodamduck.ui.component.GrayButton
import org.chosun.dodamduck.ui.component.OutlineTextField
import org.chosun.dodamduck.ui.component.PrimaryButton
import org.chosun.dodamduck.ui.component.lazy_components.PhotoCountBox
import org.chosun.dodamduck.ui.component.lazy_components.PhotoSelectionList
import org.chosun.dodamduck.ui.theme.DodamDuckTheme

@Composable
fun PostScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            PostScreenHeader()
            PhotoSelectionList(modifier = Modifier.padding(start = 12.dp, top = 23.dp))
            PostInputText(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp),
                titleText = stringResource(id = R.string.title),
                text = stringResource(id = R.string.title)
            )
            PostTransactionType(
                modifier = Modifier.padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 17.dp
                )
            )
            PostInputText(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp, top = 8.dp)
                    .height(200.dp),
                titleText = stringResource(id = R.string.detail_description),
                text = stringResource(id = R.string.detail_description_content)
            )
            GrayButton(
                modifier = Modifier.padding(start = 12.dp, top = 17.dp),
                text = stringResource(R.string.frequently_used_button)
            )
            PostInputText(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp),
                titleText = stringResource(R.string.desired_trading_location),
                text = stringResource(R.string.dummy_item_location)
            )
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                startPadding = 12.dp, endPadding = 12.dp, topPadding = 28.dp,
                elevation = 0.dp, text = stringResource(R.string.post_completed), shape = RoundedCornerShape(6.dp),
                height = 80.dp
            )
        }
    }
}

@Composable
fun PostScreenHeader() {
    Row(
        modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close Icon"
        )
        Spacer(Modifier.run { weight(0.7f) })
        DodamDuckTextH2(
            Modifier.weight(2f),
            text = stringResource(R.string.selling_my_stuff),
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        DodamDuckTextH3(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.save_temporarily),
            color = Color.Gray,
            textAlign = TextAlign.End
        )
    }
    Divider(modifier = Modifier.padding(top = 10.dp))
}

@Composable
fun PostInputText(modifier: Modifier = Modifier, titleText: String, text: String) {
    DodamDuckText(
        modifier = Modifier.padding(start = 12.dp, top = 17.dp),
        text = titleText,
        fontSize = 12,
        fontWeight = FontWeight.SemiBold
    )
    OutlineTextField(
        modifier = modifier
            .fillMaxWidth(),
        label = text,
        borderColor = Color.Gray,
        borderWidth = 1
    )
}

@Composable
fun PostTransactionType(modifier: Modifier = Modifier) {
    var selectedOption by remember { mutableStateOf("exchange") }
    val options = listOf(
        "exchange" to stringResource(R.string.exchange), "donation" to stringResource(
            R.string.donation
        )
    )

    Column(modifier = modifier) {
        DodamDuckText(
            text = stringResource(id = R.string.transaction_method),
            fontSize = 12,
            fontWeight = FontWeight.SemiBold
        )

        Row(verticalAlignment = CenterVertically) {
            options.forEach { (option, label) ->
                DodamDuckRadioButton(
                    modifier = Modifier.padding(top = 8.dp, end = 12.dp),
                    text = label,
                    selected = selectedOption == option,
                    onSelect = { selectedOption = option }
                )
            }
        }
    }
}

@Composable
@Preview
fun PostPreview() {
    DodamDuckTheme {
        PostScreen()
    }
}

@Composable
@Preview
fun PhotoCountBoxPreview() {
    PhotoCountBox()
}
