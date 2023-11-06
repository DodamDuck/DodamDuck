package org.chosun.dodamduck.model.repository

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import org.chosun.dodamduck.R
import org.chosun.dodamduck.model.state.PostDetailUserInfoState

object DummyItemFactory {
    
    @Composable
    fun createPostDetailUserInfoDummyList(): List<PostDetailUserInfoState> {
        return listOf(
            PostDetailUserInfoState(
                userName = "짱구 맘",
                userProfile = painterResource(id = R.drawable.img_user_profile),
                info = "1일전",
                content = "요새 걱정이 너무 많네요.. 도와주세요"
            ),
            
            PostDetailUserInfoState(
                userName = "유리 맘",
                userProfile = painterResource(id = R.drawable.img_user_profile_2),
                info = "1일전",
                content = "저희 애도 말을 안들어서 힘드네요.\nㅠㅠ 같이 힘내봐요"
            )
        )
    }
}