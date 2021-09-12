package com.example.testmulti

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class MainViewModel @Inject constructor(
    private val articleUseCase: ArticleUseCase
) : ViewModel() {

    private val _mutable = MutableLiveData<String>()
    val liveData: LiveData<String>
        get() = _mutable

    fun getArticles() {
        Timber.d("Entering ViewModel function")
        articleUseCase.invoke(Day(7))
            .onLoading { Timber.d("ViewModel: Loading") }
            .onSuccess {
                _mutable.value = it[0].title
                Timber.d("ViewModel: Success ${it[0].title}")
            }
            .onError { Timber.d("ViewModel: Error ${it.message}") }
            .launchIn(viewModelScope)
    }
}