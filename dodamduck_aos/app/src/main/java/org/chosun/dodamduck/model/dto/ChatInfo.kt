package org.chosun.dodamduck.model.dto

data class ChatInfo(
    val date: String,
    val message: String,
    val isOther: Boolean = false,
)