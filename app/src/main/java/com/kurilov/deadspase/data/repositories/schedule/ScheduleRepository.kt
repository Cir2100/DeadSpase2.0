package com.kurilov.deadspase.data.repositories.schedule

import com.kurilov.deadspase.data.api.ErrorResult
import com.kurilov.deadspase.data.api.PendingResult
import com.kurilov.deadspase.data.api.Result
import com.kurilov.deadspase.data.api.takeSuccess
import com.kurilov.deadspase.data.db.AppDatabase
import com.kurilov.deadspase.data.db.entry.SchedulePair
import com.kurilov.deadspase.data.db.entry.SemesterInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

//todo save cash
class ScheduleRepository @Inject constructor(
    private val scheduleLoaderInternet: ScheduleLoaderInternet,
    private val scheduleLoaderLocal: ScheduleLoaderLocal,
    private val appDatabase: AppDatabase,
) {

    var semesterInfo: SemesterInfo? = null

    private val _isWeekRed = MutableStateFlow(false)
    val isWeekRed: StateFlow<Boolean> = _isWeekRed

    private val _schedule = MutableStateFlow<Result<List<SchedulePair>>>(PendingResult())
    val schedule: StateFlow<Result<List<SchedulePair>>> = _schedule

    suspend fun init() {
        updateSemesterInfo()
    }

    suspend fun getSchedule(name: String, isInternet: Boolean, isForceLoad: Boolean = false) {
        _schedule.value = PendingResult()
        val groupId = appDatabase.groupDao().getIdByName(name)
        val teacherId = appDatabase.teacherDao().getIdByName(name)
        if (groupId == null && teacherId == null) {
            _schedule.value =  ErrorResult(Exception("Такого нет в гуапе")) //todo
        }
        _schedule.value =  if (isInternet) {
            if (isForceLoad) {
                if (groupId != null) {
                    scheduleLoaderInternet.getGroupSchedule(groupId)
                } else {
                    scheduleLoaderInternet.getTeacherSchedule(teacherId!!)
                }
            } else {
                scheduleLoaderLocal.getCash()
            }
        } else {
            scheduleLoaderLocal.getSchedule(name)
        }
    }


    private suspend fun updateSemesterInfo() {
        semesterInfo = scheduleLoaderInternet.getSemesterInfo().takeSuccess()
        val localSemesterInfo = scheduleLoaderLocal.getSemesterInfo().takeSuccess()
        if (semesterInfo?.update == localSemesterInfo?.update) return
        semesterInfo?.let {
            _isWeekRed.value = it.isWeekRed
            appDatabase.semesterInfoDao().insert(it)
            updateGroups()
            updateTeachers()
        }
    }

    private suspend fun updateGroups() {
        val groups = scheduleLoaderInternet.getGroups().takeSuccess()
        if (groups != null) {
            appDatabase.groupDao().replaceAllWhenUpdateSemester(groups)
        }

    }

    private suspend fun updateTeachers() {
        val teachers = scheduleLoaderInternet.getTeachers().takeSuccess()
        if (teachers != null) {
            appDatabase.teacherDao().replaceAllWhenUpdateSemester(teachers)
        }
    }
}
