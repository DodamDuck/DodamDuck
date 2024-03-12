package org.chosun.dodamduck.data.dto

import com.google.gson.annotations.SerializedName

data class SearchDTO (
    val id: String,
    val query:String,
    @SerializedName("search_count") val searchCount: String,
    @SerializedName("last_searched") val lastSearched: String
)

fun SearchDTO.convertCategory(): CategoryDTO
    = CategoryDTO(id = this.id, name = this.query)

fun List<SearchDTO>.convertCategoryList(): List<CategoryDTO>
    =   this.map { CategoryDTO(id = it.id, name = it.query) }