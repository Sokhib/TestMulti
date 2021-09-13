package com.example.data.di

import com.example.data.datasource.ArticleDataSourceImpl
import com.example.domain.data.ArticleDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun getArticleDataSource(articleDataSourceImpl: ArticleDataSourceImpl): ArticleDataSource

}
