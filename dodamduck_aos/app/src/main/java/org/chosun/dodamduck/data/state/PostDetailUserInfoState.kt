package org.chosun.dodamduck.data.state

import androidx.compose.ui.graphics.painter.Painter

data class PostDetailUserInfoState(
    val userName: String = "",
    val userProfile: Painter,
    val info: String = "",
    val content: String = "",
)