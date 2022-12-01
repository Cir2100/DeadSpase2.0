package com.kurilov.deadspase.data.repositories.schedule

import com.kurilov.deadspase.data.api.Result
import com.kurilov.deadspase.data.db.AppDatabase
import com.kurilov.deadspase.data.db.DatabaseExecutor
import com.kurilov.deadspase.data.db.entry.Group
import com.kurilov.deadspase.data.db.entry.SchedulePair
import com.kurilov.deadspase.data.db.entry.SemesterInfo
import com.kurilov.deadspase.data.db.entry.Teacher
import javax.inject.Inject

class ScheduleLoaderLocal @Inject constructor(
    private val appDatabase: AppDatabase,
    private val databaseExecutor: DatabaseExecutor,
)  {//: ScheduleLoader {

    suspend fun getGroups(): Result<List<Group>> =
        databaseExecutor.getData { appDatabase.groupDao().getAll() }

    suspend fun getTeachers(): Result<List<Teacher>> =
        databaseExecutor.getData { appDatabase.teacherDao().getAll() }

    suspend fun getSchedule(name: String): Result<List<SchedulePair>> =
        databaseExecutor.getData { appDatabase.schedulePairDao().getUserScheduleScheduleByName(name) }

    suspend fun getCash(): Result<List<SchedulePair>> =
        databaseExecutor.getData { appDatabase.schedulePairDao().getAllCash() }

    suspend fun getSemesterInfo(): Result<SemesterInfo> =
        databaseExecutor.getData { appDatabase.semesterInfoDao().get() }

}