package com.example.anime


import com.google.gson.annotations.SerializedName

data class ToX(
    @SerializedName("day")
    val day: Int,
    @SerializedName("month")
    val month: Int,
    @SerializedName("year")
    val year: Int
)