package com.example.domain.model

data class ArticleModel(
    val id: Long,
    val mediaUrl: String = "",
    val publishedDate: String = "",
    val title: String = "",
    val byline: String = ""
)
