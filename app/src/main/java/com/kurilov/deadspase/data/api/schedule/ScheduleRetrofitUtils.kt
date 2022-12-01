package com.kurilov.deadspase.data.api.schedule

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ScheduleRetrofitUtils {

    private const val BASE_URL = "https://api.guap.ru/rasp/custom/"
    const val GROUP_PREFIX = "group"
    const val TEACHER_PREFIX = "prep"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ScheduleApiService = getRetrofit().create(ScheduleApiService::class.java)

}