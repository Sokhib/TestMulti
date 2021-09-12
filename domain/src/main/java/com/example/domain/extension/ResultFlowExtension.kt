package com.example.domain.extension

import com.example.domain.model.Result
import kotlinx.coroutines.flow.*

@Suppress("TooGenericExceptionCaught")
fun <T> resultFlow(block: suspend () -> T): Flow<Result<T>> {
    return flow {
        emit(Result.Loading)
        try {
            emit(Result.Success(block()))
        } catch (exception: Exception) {
            emit(Result.Error(exception))
        }
    }
}

fun <T> Flow<Result<T>>.onSuccess(action: suspend (data: T) -> Unit): Flow<Result<T>> =
    onEach { result ->
        if (result is Result.Success) action(result.data)
    }

fun <T> Flow<Result<T>>.onError(action: suspend (Exception) -> Unit): Flow<Result<T>> =
    onEach { result ->
        if (result is Result.Error) action(result.exception)
    }

fun <T> Flow<Result<T>>.onLoading(action: suspend () -> Unit): Flow<Result<T>> = onEach { result ->
    if (result is Result.Loading) action()
}

fun <T, R> Flow<Result<T>>.flatMapConcatSuccess(
    transform: suspend (data: T) -> Flow<Result<R>>
): Flow<Result<R>> = flow {
    collect { result ->
        when (result) {
            is Result.Success -> emitAll(transform(result.data))
            is Result.Error -> emit(Result.Error(result.exception))
            is Result.Loading -> emit(Result.Loading)
        }
    }
}