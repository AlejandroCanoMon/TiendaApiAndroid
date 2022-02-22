package com.api.Peliculas.datos


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductObject(
    @SerializedName("data")
    @Expose
    val data: List<ProductObjectItem>,
@SerializedName("message")
val message: String,
@SerializedName("success")
val success: Boolean
)