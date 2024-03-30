package org.chosun.dodamduck.ui.navigation

import org.chosun.dodamduck.R

sealed class Screen(
    val title: Int, val icon: Int?, val screenRoute: String
) {
    object Trade :
        Screen(R.string.home, R.drawable.ic_home_48, R.string.home.toString())

    object TradeWrite :
        Screen(R.string.trade_write, null, R.string.trade_write.toString())

    object Onboarding :
        Screen(R.string.onboarding, null, R.string.onboarding.toString())

    object Register :
        Screen(R.string.register, null, R.string.register.toString())

    object Login :
        Screen(R.string.login, null, R.string.login.toString())

    object Library :
        Screen(R.string.library, R.drawable.ic_toy_48, R.string.library.toString())

    object Post :
        Screen(R.string.board, R.drawable.ic_board_48, R.string.board.toString())

    object PostWrite :
        Screen(R.string.post_write, R.drawable.ic_board_48, R.string.post_write.toString())

    object PostDetail :
        Screen(R.string.post_detail, R.drawable.ic_board_48, R.string.post_detail.toString())

    object ChatList :
        Screen(R.string.chat_list, R.drawable.ic_chat_48, R.string.chat_list.toString())

    object Chat :
        Screen(R.string.chat, null, R.string.chat.toString())

    object User :
        Screen(R.string.user, R.drawable.ic_user_48, R.string.user.toString())

    object Search :
        Screen(R.string.search, null, R.string.search.toString())
}