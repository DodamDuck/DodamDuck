package org.chosun.dodamduck.presentation.trade

import org.chosun.dodamduck.presentation.base.SideEffect

sealed class TradeSideEffect: SideEffect {

    data class Toast(val text: String): TradeSideEffect()

    object NavigatePopBackStack: TradeSideEffect()

    object NavigateToSearch: TradeSideEffect()

    object NavigateToTradeWrite: TradeSideEffect()
}