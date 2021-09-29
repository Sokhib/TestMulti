package com.example.data.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("byline")
    val byline: String?,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("media")
    val media: List<Media>?,
    @SerializedName("published_date")
    val publishedDate: String?,
    @SerializedName("title")
    val title: String?
)