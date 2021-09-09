package com.example.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaMetadata(
    @Json(name = "format")
    val format: String,
    @Json(name = "height")
    val height: Int,
    @Json(name = "url")
    val url: String,
    @Json(name = "width")
    val width: Int
)