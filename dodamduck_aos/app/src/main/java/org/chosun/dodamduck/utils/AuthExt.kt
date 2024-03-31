package org.chosun.dodamduck.utils

object AuthExt {
    fun String.verifyId(): Boolean
        = this.matches(Regex("[a-z](?=.*\\d)[a-zA-Z\\d]{7,15}$"))
}