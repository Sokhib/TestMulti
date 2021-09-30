package com.example.data.datasource

import androidx.annotation.VisibleForTesting
import com.example.data.mapper.ArticleListMapper
import com.example.data.services.NYTService
import com.example.domain.data.ArticleDataSource
import com.example.domain.extension.resultFlow
import com.example.domain.model.Day
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleDataSourceImpl @Inject constructor(
    @VisibleForTesting val nytService: NYTService,
    @VisibleForTesting val articlesMapper: ArticleListMapper
) : ArticleDataSource {
    override fun getMostPopularArticles(days: Day) = resultFlow {
        val articleResponse = nytService.fetchMostPopularArticles(days = days)
        return@resultFlow articlesMapper.map(articleResponse)
    }
}