package com.example.anime


import com.google.gson.annotations.SerializedName

data class topit2(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("links")
    val links: Links,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("pagination")
    val pagination: PaginationX
)