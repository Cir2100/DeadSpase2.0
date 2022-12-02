package com.kurilov.deadspase.data.api

sealed class Result<T> {

    fun <R> map(mapper: (T) -> R): Result<R> = when(this) {
        is PendingResult -> PendingResult()
        is ErrorResult -> ErrorResult(this.exception)
        is SuccessResult -> {
            SuccessResult(mapper(this.data))
        }
    }

}

class PendingResult<T>: Result<T>()

class SuccessResult<T>(
    val data: T,
): Result<T>()

class ErrorResult<T>(
    val exception: java.lang.Exception,
): Result<T>()


fun <T> Result<T>.takeSuccess(): T? {
    return if (this is SuccessResult) {
        data
    } else {
        null
    }
}