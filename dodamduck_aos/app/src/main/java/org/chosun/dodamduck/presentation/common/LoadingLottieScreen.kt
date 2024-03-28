package org.chosun.dodamduck.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import org.chosun.dodamduck.R
import org.chosun.dodamduck.ui.component.BottomRoundedBox
import org.chosun.dodamduck.ui.component.DodamDuckIcon
import org.chosun.dodamduck.ui.component.WelcomeText
import org.chosun.dodamduck.ui.theme.DodamDuckTheme
import org.chosun.dodamduck.ui.theme.Primary


@Composable
fun LoadingLottieScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.toy_lottie))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary),
        horizontalAlignment = CenterHorizontally
    ) {
        BottomRoundedBox(
            modifier = Modifier.height(400.dp),
            startRound = 200
        ) {
            Box(modifier = Modifier.align(CenterHorizontally)) {
                DodamDuckIcon(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .fillMaxWidth()
                        .align(Alignment.TopCenter),
                    size = 300
                )
            }
        }

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        WelcomeText()
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingLottieScreen() {
    DodamDuckTheme {
        LoadingLottieScreen()
    }
}