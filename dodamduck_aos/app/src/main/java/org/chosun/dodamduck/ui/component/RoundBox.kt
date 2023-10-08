import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import org.chosun.dodamduck.ui.theme.Secondary

@Composable
fun BottomStartRoundedBox(
    modifier: Modifier = Modifier,
    startRound: Int = 0
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp, 0.dp, 0.dp, startRound.dp))
            .background(Secondary)
    )
}

@Preview
@Composable
fun PreviewRoundedBottomBox() {
    BottomStartRoundedBox()
}
