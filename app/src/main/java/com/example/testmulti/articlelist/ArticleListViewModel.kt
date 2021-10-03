package com.example.testmulti.articlelist

import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.domain.extension.onError
import com.example.domain.extension.onLoading
import com.example.domain.extension.onSuccess
import com.example.domain.model.ArticleModel
import com.example.domain.model.Day
import com.example.domain.usecase.ArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    @VisibleForTesting val useCase: ArticleUseCase
) : BaseViewModel() {

    private val _articleListState = MutableLiveData<ArticleListViewState>()
    val articleListState: LiveData<ArticleListViewState>
        get() = _articleListState

    private val _articleListData = MutableLiveData<List<ArticleModel>>()
    val articleListData
        get() = _articleListData

    var articleError = ObservableField<String>()

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        useCase(Day(1))
            .onLoading {
                _articleListState.postValue(ArticleListViewState.Loading)
            }
            .onSuccess { articles ->
                if (articles.isEmpty()) {
                    _articleListState.postValue(ArticleListViewState.Empty)
                    _articleListData.postValue(emptyList())
                } else {
                    _articleListData.postValue(articles)
                    _articleListState.postValue(ArticleListViewState.Loaded)
                }
            }
            .onError {
                //TODO: Also showMessage() toast can be used
                _articleListState.postValue(ArticleListViewState.Error)
                articleError.set(it.message)
            }
            .launchIn(viewModelScope)
    }

}