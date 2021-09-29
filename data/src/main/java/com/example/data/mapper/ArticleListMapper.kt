package com.example.data.mapper

import com.example.data.model.PopularArticles
import com.example.domain.mapper.Mapper
import com.example.domain.model.ArticleModel
import javax.inject.Inject

class ArticleListMapper @Inject constructor() : Mapper<PopularArticles, List<ArticleModel>> {
    override suspend fun map(from: PopularArticles): List<ArticleModel> =
        from.results.takeIf { !it.isNullOrEmpty() }?.map { article ->
            ArticleModel(
                id = article.id!!,
                mediaUrl = article.media.takeIf { !it.isNullOrEmpty() }
                    ?.get(0)?.mediaMetadata.takeIf { !it.isNullOrEmpty() }?.get(0)?.url.orEmpty(),
                publishedDate = article.publishedDate ?: "Date",
                title = article.title ?: "Title",
                byline = article.byline ?: "By Line"
            )
        } ?: emptyList()
}