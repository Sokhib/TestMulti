package com.example.data.model


import com.google.gson.annotations.SerializedName

data class PopularArticles(
    @SerializedName("results")
    val results: List<Result>?,
    @SerializedName("status")
    val status: String?
)