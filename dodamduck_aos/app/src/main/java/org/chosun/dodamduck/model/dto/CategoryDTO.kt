package org.chosun.dodamduck.model.dto

import com.google.gson.annotations.SerializedName

data class CategoryDTO(
    @SerializedName("category_id") val id: String,
    var name: String
)