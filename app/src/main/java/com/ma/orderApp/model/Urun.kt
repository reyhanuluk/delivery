package com.ma.orderApp.model

import com.google.gson.annotations.SerializedName

/**
created by Mehmet E. Yıldız
 **/
data class Urun(
    @SerializedName("id")
    var id: String,
    @SerializedName("urunResimURL")
    var urunResimURL: String,
    @SerializedName("urunAdi")
    var urunAdi: String,
    @SerializedName("urunFiyati")
    var urunFiyati: Double,
    @SerializedName("kategoriId")
    var kategoriId : Int
)