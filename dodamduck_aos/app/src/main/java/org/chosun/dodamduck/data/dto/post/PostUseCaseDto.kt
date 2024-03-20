package org.chosun.dodamduck.data.dto.post

import okhttp3.MultipartBody

data class PostUseCaseDto(
    val postId: String? = null,
    val userId: String,
    val categoryId: String? = null,
    val title: String? = null,
    val content: String? = null,
    val location: String? = null,
    val image: MultipartBody.Part? = null
)