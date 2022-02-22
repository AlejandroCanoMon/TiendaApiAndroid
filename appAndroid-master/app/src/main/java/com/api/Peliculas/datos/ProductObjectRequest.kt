package com.api.Peliculas.datos

import com.google.gson.annotations.SerializedName

data class ProductObjectRequest(
    @SerializedName("pack")
    val pack: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)
