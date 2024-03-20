package org.chosun.dodamduck.network.response

data class OpenApiResponse<T> (
    val page: Int,
    val perPage: Int,
    val totalCount: Int,
    val currentCount: Int,
    val matchCount: Int,
    val data: T? = null
)