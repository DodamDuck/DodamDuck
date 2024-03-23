package org.chosun.dodamduck.ui.modifier

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput

inline fun Modifier.addFocusCleaner(
    focusManager: FocusManager,
    crossinline doOnClear: () -> Unit = {}
): Modifier = this.pointerInput(Unit) {
    detectTapGestures(onTap = {
        doOnClear()
        focusManager.clearFocus()
    })
}