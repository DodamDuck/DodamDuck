package org.chosun.dodamduck.model.repository

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import org.chosun.dodamduck.R
import org.chosun.dodamduck.model.dto.PostCommentDTO
import org.chosun.dodamduck.model.dto.PostDetailDTO
import org.chosun.dodamduck.model.state.PostDetailUserInfoState

object DummyItemFactory {

    @Composable
    fun createPostDetailUserInfoDummyList(): List<PostDetailUserInfoState> {
        return listOf(
            PostDetailUserInfoState(
                userName = "짱구 맘",
                userProfile = painterResource(id = R.drawable.img_user_profile_2),
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

    fun createPostDetailDto() = PostDetailDTO(
        "다양한 보드게임 원합니다.",
        "가족과 함께 즐길 수 있는 다양한 보드게임, 교환 원합니다.",
        "http://sy2978.dothome.co.kr/uploads/post_id18.jpg",
        "2023-11-12 17:49:15",
        "인천",
        "21",
        "홍길동",
        "user1",
        "5",
        "교환",
        "http://sy2978.dothome.co.kr/uploads/post_id18.jpg"
    )

    fun createPostComment() = PostCommentDTO(
        "1",
        "1",
        "user1",
        "저랑 교환 하실래요?",
        "2023-11-12 17:49:15",
        "2023-11-12 17:49:15",
        "김철수",
        "2",
        "광주 동명동",
        ""
    )
}