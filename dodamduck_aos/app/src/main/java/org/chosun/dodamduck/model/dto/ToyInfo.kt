package org.chosun.dodamduck.model.dto

import com.google.gson.annotations.SerializedName

data class ToyInfo(
    @SerializedName("순번")
    val sequenceNumber: String,
    @SerializedName("관리기관명")
    val managementAgencyName: String,
    @SerializedName("관리기관전화번호")
    val managementAgencyPhoneNumber: String,
    @SerializedName("소재지도로명주소")
    val addressStreet: String,
    @SerializedName("소재지지번주소")
    val addressParcel: String,
    @SerializedName("장난감명")
    val toyName: String,
    @SerializedName("영 역")
    val area: String,
    @SerializedName("사용연령")
    val ageGroup: String,
    @SerializedName("대여료")
    val rentalFee: String,
    @SerializedName("제조사")
    val manufacturer: String
)