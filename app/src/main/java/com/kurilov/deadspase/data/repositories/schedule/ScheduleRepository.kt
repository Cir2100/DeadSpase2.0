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

    suspend fun getSchedule(
        name: String,
        week: Boolean,
        day: Int,
        isInternet: Boolean,
        isForceLoad: Boolean = false) {
        _schedule.value = PendingResult()
        val groupId = appDatabase.groupDao().getIdByName(name)
        val teacherId = appDatabase.teacherDao().getIdByName(name)
        val weekInt = if (week) 1 else 2
        val dayNew = if (day == 6) 0 else day + 1
        if (groupId == null && teacherId == null) {
            _schedule.value =  ErrorResult(Exception("Такого нет в гуапе")) //todo
        } else {
            _schedule.value =  if (isInternet) {
                if (isForceLoad) {
                    val internetSchedule = if (groupId != null) {
                        scheduleLoaderInternet.getGroupSchedule(groupId)
                    } else {
                        scheduleLoaderInternet.getTeacherSchedule(teacherId!!)
                    }
                    internetSchedule.takeSuccess()?.let { schedulePairs -> //todo
                        appDatabase.schedulePairDao().saveCash(schedulePairs.map {
                            it.copy(isInternetCash = true)
                        })
                    }
                }
                scheduleLoaderLocal.getDayCash(weekInt, dayNew)
            } else {
                scheduleLoaderLocal.getSchedule(name, weekInt, dayNew)
            }
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
