package com.example.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Media(
    @Json(name = "approved_for_syndication")
    val approvedForSyndication: Int,
    @Json(name = "caption")
    val caption: String,
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "media-metadata")
    val mediaMetadata: List<MediaMetadata>,
    @Json(name = "subtype")
    val subtype: String,
    @Json(name = "type")
    val type: String
)