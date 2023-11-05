package org.chosun.dodamduck.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.component.DodamDuckTextH2
import org.chosun.dodamduck.ui.component.DodamDuckTextH3
import org.chosun.dodamduck.ui.theme.DodamDuckTheme

@Composable
fun PostScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = stringResource(id = R.string.board),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun PostScreenHeader(navController: NavHostController) {
    Row(
        modifier = Modifier.padding(top = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {navController.popBackStack()}, modifier = Modifier.wrapContentSize()) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close Icon"
            )
        }
        Spacer(Modifier.run { weight(0.5f) })
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

@Preview
@Composable
fun PostPreview() {
    DodamDuckTheme {
        PostScreen()
    }
}