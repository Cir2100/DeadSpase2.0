package com.kurilov.deadspase.data.api.schedule

import com.kurilov.deadspase.data.db.entry.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleApiService {

    @GET("get-sem-info")
    suspend fun getSemesterInfo(): Response<SemesterInfo>

    @GET("get-sem-groups")
    suspend fun getAllGroups(): Response<List<Group>>

    @GET("get-sem-preps")
    suspend fun getAllTeaches(): Response<List<Teacher>>

    @GET("get-sem-builds")
    suspend fun getAllBuildings(): Response<List<Building>>

    @GET("get-sem-rasp/{prefix}{id}")
    suspend fun getSchedule(
        @Path(value = "prefix", encoded = false) prefix: String,
        @Path(value = "id", encoded = false) group: Long
    ): Response<List<SchedulePair>>


}