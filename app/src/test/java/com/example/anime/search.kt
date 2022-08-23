package com.example.anime


import com.google.gson.annotations.SerializedName

data class search(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("pagination")
    val pagination: Pagination
)