package com.example.domain.data

import com.example.domain.model.ArticleModel
import com.example.domain.model.Day
import com.example.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface ArticleDataSource {
    fun getMostPopularArticles(days: Day): Flow<Result<List<ArticleModel>>>
}