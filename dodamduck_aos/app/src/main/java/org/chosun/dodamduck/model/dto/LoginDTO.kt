package org.chosun.dodamduck.model.dto

data class LoginDTO(
    val userID: String,
    val userName: String,
    val location: String,
    val verification_count: String,
    val level: String,
    val profile_url: String,
    val login_success: Boolean
)