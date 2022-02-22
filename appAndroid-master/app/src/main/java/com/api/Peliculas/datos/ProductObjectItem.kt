package com.api.Peliculas.datos


import com.google.gson.annotations.SerializedName

data class ProductObjectItem(
    @SerializedName("pack")
    val pack: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)