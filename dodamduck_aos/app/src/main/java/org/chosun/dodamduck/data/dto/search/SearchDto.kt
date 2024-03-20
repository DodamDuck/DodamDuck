package org.chosun.dodamduck.data.dto.search

import com.google.gson.annotations.SerializedName
import org.chosun.dodamduck.data.dto.post.CategoryDto

data class SearchDTO (
    val id: String,
    val query:String,
    @SerializedName("search_count") val searchCount: String,
    @SerializedName("last_searched") val lastSearched: String
)

fun SearchDTO.convertCategory(): CategoryDto
    = CategoryDto(id = this.id, name = this.query)

fun List<SearchDTO>.convertCategoryList(): List<CategoryDto>
    =   this.map { CategoryDto(id = it.id, name = it.query) }