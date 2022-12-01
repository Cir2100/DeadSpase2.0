package com.kurilov.deadspase.data.repositories.schedule

import com.kurilov.deadspase.data.api.Result
import com.kurilov.deadspase.data.api.RetrofitExecutor
import com.kurilov.deadspase.data.api.schedule.ScheduleApiService
import com.kurilov.deadspase.data.api.schedule.ScheduleRetrofitUtils
import com.kurilov.deadspase.data.db.entry.Group
import com.kurilov.deadspase.data.db.entry.SchedulePair
import com.kurilov.deadspase.data.db.entry.SemesterInfo
import com.kurilov.deadspase.data.db.entry.Teacher
import javax.inject.Inject


class ScheduleLoaderInternet @Inject constructor(
    private val apiService: ScheduleApiService,
    private val retrofitExecutor: RetrofitExecutor,
) {//: ScheduleLoader {

    suspend fun getGroups(): Result<List<Group>> =
         retrofitExecutor.getData { apiService.getAllGroups() }

    suspend fun getTeachers(): Result<List<Teacher>> =
        retrofitExecutor.getData { apiService.getAllTeaches() }

    suspend fun getGroupSchedule(id: Long): Result<List<SchedulePair>> =
        retrofitExecutor.getData { apiService.getSchedule(ScheduleRetrofitUtils.GROUP_PREFIX, id) }

    suspend fun getTeacherSchedule(id: Long): Result<List<SchedulePair>> =
        retrofitExecutor.getData { apiService.getSchedule(ScheduleRetrofitUtils.TEACHER_PREFIX, id) }

    suspend fun getSemesterInfo(): Result<SemesterInfo> =
        retrofitExecutor.getData { apiService.getSemesterInfo() }

}