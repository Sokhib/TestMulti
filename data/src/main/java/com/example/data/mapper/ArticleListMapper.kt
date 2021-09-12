package com.example.data.mapper

import com.example.data.model.PopularArticles
import com.example.domain.model.ArticleModel

class ArticleListMapper : Mapper<PopularArticles, List<ArticleModel>> {
    override suspend fun map(from: PopularArticles): List<ArticleModel> =
        from.results.takeIf { it.isNotEmpty() }?.map { article ->
            ArticleModel(
                id = article.id,
                mediaUrl = article.media.takeIf { it.isNotEmpty() }
                    ?.get(0)?.mediaMetadata.takeIf { it!!.isNotEmpty() }?.get(0)?.url.orEmpty(),
                publishedDate = article.publishedDate,
                title = article.title,
                byline = article.byline
            )
        } ?: emptyList()
}