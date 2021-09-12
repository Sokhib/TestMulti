package com.example.data.datasource

import com.example.data.mapper.ArticleListMapper
import com.example.data.services.NYTService
import com.example.domain.data.ArticleDataSource
import com.example.domain.extension.resultFlow
import com.example.domain.model.ArticleModel
import com.example.domain.model.Day
import com.example.domain.model.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleDataSourceImpl @Inject constructor(
    private val nytService: NYTService,
    private val articlesMapper: ArticleListMapper
) :
    ArticleDataSource {
    override fun getMostPopularArticles(days: Day): Flow<Result<List<ArticleModel>>> = resultFlow {
        val articleResponse = nytService.fetchMostPopularArticles(days = days.days)
        return@resultFlow articlesMapper.map(articleResponse)
    }
}