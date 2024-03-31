package org.chosun.dodamduck.utils

object AuthExt {
    fun String.verifyId(): Boolean
        = this.matches(Regex("[a-z](?=.*\\d)[a-zA-Z\\d]{7,15}$"))

    fun String.verifyPassword(): Boolean
        = this.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z][a-zA-Z\\d]{7,15}$"))
}