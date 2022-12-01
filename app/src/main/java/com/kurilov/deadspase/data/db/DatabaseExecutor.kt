package com.kurilov.deadspase.data.db

import com.kurilov.deadspase.data.api.ErrorResult
import com.kurilov.deadspase.data.api.Result
import com.kurilov.deadspase.data.api.SuccessResult
import javax.inject.Inject

class DatabaseExecutor @Inject constructor() {

    suspend fun <T> getData(request: suspend () -> T): Result<T> {
        return try {
            SuccessResult(request.invoke())
        } catch (exception: Exception) {
            ErrorResult(exception)
        }
    }

}