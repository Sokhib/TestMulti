package com.example.testmulti

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TestIntentViewModel @Inject constructor() : ViewModel() {
    private var two = 1
    fun increase() {
        ++two
    }

    fun print() {
        Timber.d("Two: $two")
    }

}