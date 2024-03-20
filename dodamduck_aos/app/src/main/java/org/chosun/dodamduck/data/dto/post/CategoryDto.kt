package org.chosun.dodamduck.data.dto.post

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("category_id") val id: String,
    var name: String
)