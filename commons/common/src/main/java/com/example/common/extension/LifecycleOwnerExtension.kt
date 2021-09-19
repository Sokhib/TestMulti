package com.example.common.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.common.helper.Event

inline fun <T> LifecycleOwner.observe(liveData: LiveData<T>, crossinline observer: (T) -> Unit) {
    liveData.observe(this) {
        it?.let { t -> observer(t) }
    }

}


inline fun <T> LifecycleOwner.observeSingle(
    liveData: LiveData<Event<T>>,
    crossinline observer: (T) -> Unit
) {
    liveData.observe(this) {
        it?.getContentIfNotHandled()?.let { t ->
            observer(t)
        }
    }
}
