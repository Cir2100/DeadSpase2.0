package com.kurilov.deadspase.data.repositories.schedule

import com.kurilov.deadspase.data.api.Result
import com.kurilov.deadspase.data.db.entry.Group
import com.kurilov.deadspase.data.db.entry.SchedulePair
import com.kurilov.deadspase.data.db.entry.SemesterInfo
import com.kurilov.deadspase.data.db.entry.Teacher

interface ScheduleLoader {

    suspend fun getGroups() : Result<List<Group>>

    suspend fun getTeachers() : Result<List<Teacher>>

    suspend fun getGroupSchedule(name: String) : Result<List<SchedulePair>>

    suspend fun getTeacherSchedule(name: String) : Result<List<SchedulePair>>

    suspend fun getSemesterInfo() : Result<SemesterInfo>

}