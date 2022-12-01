package com.kurilov.deadspase.data.api

import retrofit2.Response
import javax.inject.Inject


class RetrofitExecutor @Inject constructor() {
    //todo check internet connection
    suspend fun <T> getData(request: suspend () -> Response<T>): Result<T> {
        return try {
            val response = request.invoke()
            if (response.isSuccessful && response.body() != null)
                SuccessResult(response.body()!!)
            else
                throw Exception("Error") //todo
        } catch (exception: Exception) {
            ErrorResult(exception)
        }
    }

}