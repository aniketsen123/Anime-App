package com.example.anime


import com.google.gson.annotations.SerializedName

data class SearchingItem(
    @SerializedName("animeId")
    val animeId: String,
    @SerializedName("animeImg")
    val animeImg: String,
    @SerializedName("animeTitle")
    val animeTitle: String,
    @SerializedName("animeUrl")
    val animeUrl: String,
    @SerializedName("status")
    val status: String
)