package com.example.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {
    private val _progressState = MutableLiveData(0)
    val progressState: LiveData<Int>
        get() = _progressState


    private val _messageText = MutableStateFlow<String?>(null)
    val messageText: StateFlow<String?>
        get() = _messageText

    fun showMessage(text: String?) {
        _messageText.value = text
    }

    fun increaseProgress() {
        _progressState.value = _progressState.value?.plus(1)
    }

    fun decreaseProgress() {
        _progressState.value = _progressState.value?.minus(1)
    }

}