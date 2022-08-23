package com.example.anime


import com.google.gson.annotations.SerializedName

data class TitleX(
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String
)