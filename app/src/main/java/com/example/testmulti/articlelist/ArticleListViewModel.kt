package com.example.testmulti.articlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.common.base.BaseViewModel
import com.example.domain.extension.onError
import com.example.domain.extension.onLoading
import com.example.domain.extension.onSuccess
import com.example.domain.model.Day
import com.example.domain.usecase.ArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    useCase: ArticleUseCase
) : BaseViewModel() {

    private val _articleListState = MutableLiveData<ArticleListViewState>()
    val articleListState: LiveData<ArticleListViewState>
        get() = _articleListState

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean>
        get() = _state

    init {
        useCase(Day(1))
            .onLoading {
                _articleListState.postValue(ArticleListViewState.Loading)
            }
            .onSuccess { articles ->
                if (articles.isEmpty()) {
                    _articleListState.postValue(ArticleListViewState.Empty)
                } else {
                    Timber.d(articles[0].title)
                    _state.value = true
                    _articleListState.postValue(ArticleListViewState.Loaded)
                }
            }
            .onError {
                _articleListState.postValue(ArticleListViewState.Error)
            }
            .launchIn(viewModelScope)
    }

}