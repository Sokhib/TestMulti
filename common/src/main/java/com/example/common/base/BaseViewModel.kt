package com.example.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.common.helper.Event

abstract class BaseViewModel : ViewModel() {
    private val _progressState = MutableLiveData(0)
    val progressState: LiveData<Int>
        get() = _progressState


    private val _messageText = MutableLiveData<Event<String>>()
    val messageText: LiveData<Event<String>>
        get() = _messageText

    fun showMessage(text: String?) {
        text?.let {
            _messageText.value = Event(text)
        }
    }

    fun increaseProgress() {
        _progressState.value = _progressState.value?.plus(1)
    }

    fun decreaseProgress() {
        _progressState.value = _progressState.value?.minus(1)
    }

}