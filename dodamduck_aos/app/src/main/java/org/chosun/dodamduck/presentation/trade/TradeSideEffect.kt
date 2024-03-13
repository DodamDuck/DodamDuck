package org.chosun.dodamduck.presentation.trade

import org.chosun.dodamduck.presentation.base.SideEffect

sealed class TradeSideEffect: SideEffect {
    object NavigatePopBackStack: TradeSideEffect()

    object NavigateToSearch: TradeSideEffect()

    object NavigateToTradeWrite: TradeSideEffect()
}