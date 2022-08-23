package com.example.anime


import com.google.gson.annotations.SerializedName

data class topit(
    @SerializedName("data")
    val `data`: List<DataX>,
    @SerializedName("links")
    val links: Links,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("pagination")
    val pagination: PaginationX
)