package com.example.domain.usecase

import androidx.annotation.VisibleForTesting
import com.example.common.di.DefaultDispatcher
import com.example.domain.data.ArticleDataSource
import com.example.domain.model.ArticleModel
import com.example.domain.model.Day
import com.example.domain.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleUseCase @Inject constructor(
    @VisibleForTesting val articleDataSource: ArticleDataSource,
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
) : FlowUseCase<Day, List<ArticleModel>>(defaultDispatcher) {
    override fun execute(parameters: Day): Flow<Result<List<ArticleModel>>> {
        return articleDataSource.getMostPopularArticles(days = parameters)
    }
}