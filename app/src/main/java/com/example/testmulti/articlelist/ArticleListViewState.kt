package com.example.testmulti.articlelist

sealed class ArticleListViewState {
    object Loaded : ArticleListViewState()
    object Loading : ArticleListViewState()
    object Error : ArticleListViewState()
    object Empty : ArticleListViewState()

    fun isLoaded() = this is Loaded
    fun isLoading() = this is Loading
    fun isError() = this is Error
    fun isEmpty() = this is Empty

}