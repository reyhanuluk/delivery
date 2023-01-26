package com.ma.orderApp.model

import com.google.gson.annotations.SerializedName

data class Siparis(
    @SerializedName("id")
    var id: String,
    @SerializedName("urunResimURL")
    var urunResimURL: String,
    @SerializedName("urunAdi")
    var urunAdi: String,
    @SerializedName("masaNumarasi")
    var masaNumarasi: Int,
    @SerializedName("isCompleted")
    var isCompleted: Boolean,
)
