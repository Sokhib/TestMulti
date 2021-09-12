package com.example.data.di

import com.example.data.datasource.ArticleDataSourceImpl
import com.example.data.mapper.ArticleListMapper
import com.example.data.services.NYTService
import com.example.domain.data.ArticleDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideDataSource(
        articleListMapper: ArticleListMapper,
        nytService: NYTService
    ): ArticleDataSource =
        ArticleDataSourceImpl(nytService, articleListMapper)
}
