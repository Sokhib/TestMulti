package com.example.domain.model

data class ArticleModel(
    val id: Long = 0L,
    val mediaUrl: String = "",
    val publishedDate: String = "",
    val title: String = "",
    val byline: String = ""
)
