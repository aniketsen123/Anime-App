package com.example.anime


import com.google.gson.annotations.SerializedName

data class searchit2(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("pagination")
    val pagination: Pagination
)