package com.example.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularArticles(
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "num_results")
    val numResults: Int,
    @Json(name = "results")
    val results: List<Result>,
    @Json(name = "status")
    val status: String
)