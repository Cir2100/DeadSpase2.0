package com.kurilov.deadspase.data.api

sealed class Result<T>

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